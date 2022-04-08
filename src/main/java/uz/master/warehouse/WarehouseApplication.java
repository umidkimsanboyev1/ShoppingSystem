package uz.master.warehouse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import uz.master.warehouse.dto.auth.AuthCreateDto;
import uz.master.warehouse.enums.Role;
import uz.master.warehouse.properties.OpenApiProperties;
import uz.master.warehouse.properties.ServerProperties;
import uz.master.warehouse.services.auth.AuthUserService;

@SpringBootApplication
@OpenAPIDefinition
@RequiredArgsConstructor
@EnableConfigurationProperties({
        ServerProperties.class,
        OpenApiProperties.class}
)
public class WarehouseApplication {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserService service;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }


    @Bean
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {
            service.createUser(new AuthCreateDto
                    (1L, "jasur", "L", "+998941453337", "123", Role.ADMIN.name(),1L));
        };
        runner.run("s", "b");
    }


}
