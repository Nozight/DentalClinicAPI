/*
package clinic.controller;

import clinic.security.model.AppUser;
import clinic.security.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataLoader dataLoader;

    @PostMapping("/login")
    public ResponseEntity<?> authByPost(@RequestBody AppUser appUser) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ResponseEntity<>(dataLoader.mapDTO(appUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Log failed", HttpStatus.UNAUTHORIZED);
        }

    }
}
*/
