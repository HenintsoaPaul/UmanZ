package mg.itu.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class RHApplication {

    public static void main( String[] args ) {
        SpringApplication.run( RHApplication.class, args );
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("UmanZ RH API").version("1.0.0"))
                .addServersItem(new Server().url("/api").description("Reverse Proxy URL"));
    }
}
