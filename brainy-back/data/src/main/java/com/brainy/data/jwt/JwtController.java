package com.brainy.data.jwt;

import com.brainy.data.domain.BrainyUser;
import com.brainy.data.service.BrainyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class JwtController {

    @Autowired
    private BrainyUserService brainyUserService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@AuthenticationPrincipal OidcUser user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
        }

        String email = user.getEmail();
        BrainyUser registeredUser = brainyUserService.findUserByEmail(email);

        if (registeredUser == null || !registeredUser.isEnabled()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accesso negato");
        }

        String token = JwtUtil.generateToken(email);
        return ResponseEntity.ok(token);
    }

}
