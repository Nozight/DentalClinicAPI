package clinic.config;

import clinic.service.impl.AppointmentService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class SpringConfig {

    @Bean
    public clinic.service.impl.AppointmentService appointmentService(){
        return new AppointmentService();
    }

}
