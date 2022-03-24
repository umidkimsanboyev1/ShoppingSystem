package uz.master.warehouse.services.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.auth.AuthUserDto;
import uz.master.warehouse.dto.auth.SessionDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.AuthUser;
import uz.master.warehouse.properties.ServerProperties;
import uz.master.warehouse.repository.AuthUserRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final AuthUserRepository repository;
  private  final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsername(username).orElseThrow(() -> {
            throw new RuntimeException("user not found");
        });
        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(new SimpleGrantedAuthority(user.getRole().name())).build();
    }
    public ResponseEntity<DataDto<SessionDto>> login(AuthUserDto dto) {
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
                SessionDto sessionDto = SessionDto.builder()
                        .accessToken(json_auth.get("access_token").asText())
                        .refreshToken(json_auth.get("refresh_token").asText())
                        .build();
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto("bad Request"," ",HttpStatus.BAD_REQUEST)),HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(  "bad request","",HttpStatus.INTERNAL_SERVER_ERROR)),HttpStatus.OK);
        }
    }

}
