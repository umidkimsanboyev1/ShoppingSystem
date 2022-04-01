package uz.master.warehouse.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.auth.AuthCreateDto;
import uz.master.warehouse.dto.auth.AuthUpdateDto;
import uz.master.warehouse.dto.auth.AuthUserDto;
import uz.master.warehouse.dto.auth.SessionDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.enums.Role;
import uz.master.warehouse.mapper.auth.AuthUserMapper;
import uz.master.warehouse.properties.ServerProperties;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;
    private final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;

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

        AuthUser authUser = mapper.fromCreateDto(dto);
        authUser.setBlocked(false);
        authUser.setDeleted(false);
        try {
            return new DataDto<>(repository.save(authUser).getId());

        }

        catch (Exception e) {
            return new DataDto<>(new AppErrorDto(HttpStatus.IM_USED, "already Taken", "auth/user/create"));
        }


    }

    public void delete(Long id, Long adminId) {
        if(repository.findById(adminId).orElseThrow().getRole().equals(Role.ADMIN)){
            repository.delete(id);
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
}
