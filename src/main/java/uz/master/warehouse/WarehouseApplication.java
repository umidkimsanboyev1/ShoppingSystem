package uz.master.warehouse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.master.warehouse.properties.OpenApiProperties;
import uz.master.warehouse.properties.ServerProperties;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties({
        ServerProperties.class,
        OpenApiProperties.class}
)
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
