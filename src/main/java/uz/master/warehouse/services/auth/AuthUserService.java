package uz.master.warehouse.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.dto.auth.*;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.enums.Role;
import uz.master.warehouse.mapper.auth.AuthUserMapper;
import uz.master.warehouse.properties.ServerProperties;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.services.file.FileStorageService;
import uz.master.warehouse.services.organization.OrganizationService;
import uz.master.warehouse.utils.JwtUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;
    private final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;
   private final PasswordEncoder passwordEncoder;
   private final OrganizationService service;
   private final FileStorageService fileStorageService;

    private  Path root = Paths.get("D:/uploads");
//    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


   public DataDto<AuthDto>get(){
       String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       AuthUser authUser = repository.findByUsernameAndDeletedFalse(principal).get();
       AuthDto authDto = mapper.toDto(authUser);
       return new DataDto<>(authDto);
   }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new RuntimeException("user not found");
        });
        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(new SimpleGrantedAuthority(user.getRole().name())).build();
    }

    private User loadUser(String username){
        AuthUser user = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new RuntimeException("user not found");
        });
        return new User(user.getUsername(),user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())));

    }

    public DataDto<SessionDto> login(AuthUserDto dto) {
        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (!json_auth.has("error")) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new DataDto<>(sessionDto);
            }
            return new DataDto<>(new AppErrorDto("bad Request", " ", HttpStatus.BAD_REQUEST));

        } catch (IOException e) {
            return new DataDto<>(new AppErrorDto("bad request", "", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    public DataDto<Long> createUser(AuthCreateDto dto) {
        Role role = Role.ADMIN.checkRole(dto.getRole());
        service.get(dto.getOrganizationId());
        AuthUser authUser = mapper.fromCreateDto(dto);
        authUser.setBlocked(false);
        authUser.setDeleted(false);
        authUser.setRole(role);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        try {
            return new DataDto<>(repository.save(authUser).getId());

        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto(HttpStatus.IM_USED, "already Taken phone or username ", "auth/user/create"));
        }


    }

    public void delete(Long id, Long adminId) {
        if(repository.findById(adminId).orElseThrow().getRole().equals(Role.ADMIN)){
            repository.delete(id, UUID.randomUUID().toString());
        }
    }

    public DataDto<Long> update(AuthUpdateDto dto) {
        AuthUser user = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new UsernameNotFoundException("user not found");
        });

        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());
        repository.save(user);
        return new DataDto<>(dto.getId());

    }


    private User read(String token){
        DecodedJWT decodedJWT = JwtUtils.verifier().verify(token);
        String username = decodedJWT.getSubject();
        return loadUser(username);
    }


    public DataDto<SessionDto> refreshToken(String token, HttpServletRequest request) {
        User user = read(token);
        Date expiryForAccessToken = JwtUtils.getExpireDate();
        Date expiryForRefreshToken = JwtUtils.getExpireDateForRefreshToken();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForAccessToken)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(JwtUtils.getAlgorithm());

        return new DataDto<>(SessionDto.builder().accessToken(accessToken)
                .expiresIn(expiryForAccessToken.getTime())
                .refreshToken(token)
                .refreshTokenExpire(expiryForRefreshToken.getTime())
                .issuedAt(System.currentTimeMillis())
                .build());
    }


    public void savePicture(MultipartFile picture) throws IOException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String contentType = com.google.common.io.Files.getFileExtension(picture.getOriginalFilename());
        if("jpg".equals(contentType)||"png".equals(contentType)){
            String store = fileStorageService.store(picture);
            repository.updatePicture(store,username);
        }else {
            throw new RuntimeException("picture content type error");
        }
    }

}
