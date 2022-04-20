package clinic.controller;

import clinic.dto.AppUserDTO;
import clinic.model.AppUser;
import clinic.repository.UserRepository;
import clinic.service.security.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    DataLoader dataLoader;

    @PostMapping("/register")
    public AppUserDTO register(@RequestBody AppUser appUser){
        return dataLoader.register(appUser);
    }
}
