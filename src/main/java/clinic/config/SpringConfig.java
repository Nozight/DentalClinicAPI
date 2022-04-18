package clinic.config;

import clinic.service.impl.AppointmentService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public clinic.service.impl.AppointmentService appointmentService(){
        return new AppointmentService();
    }
}
