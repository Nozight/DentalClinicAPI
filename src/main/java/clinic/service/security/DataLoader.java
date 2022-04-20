package clinic.service.security;

import clinic.dto.AppUserDTO;
import clinic.dto.DentistDTO;
import clinic.model.AppUser;
import clinic.model.AppUserRole;
import clinic.model.Dentist;
import clinic.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public AppUserDTO mapDTO(AppUser appUser){
        return objectMapper.convertValue(appUser,AppUserDTO.class);
    }
    public AppUser mapEntity(AppUserDTO appUserDTO){        return objectMapper.convertValue(appUserDTO,AppUser.class);
    }

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("root");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password");
        userRepository.save(new AppUser("root", "root", "root", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("Tomas", "tomas", "tomas@digital.com", hashedPassword2, AppUserRole.USER));
    }
    public AppUserDTO register(AppUser appUser){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        appUser.setAppUserRole(AppUserRole.USER);
        return mapDTO(userRepository.save(appUser));
    }
}