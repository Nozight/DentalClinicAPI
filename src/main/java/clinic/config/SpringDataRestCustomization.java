package clinic.config;

import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringDataRestCustomization extends RepositoryRestMvcAutoConfiguration {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestMvcAutoConfiguration config) {
        config.getCorsRegistry().addMapping("/**")
                .allowedOrigins("http://localhost:9000");
    }
}
