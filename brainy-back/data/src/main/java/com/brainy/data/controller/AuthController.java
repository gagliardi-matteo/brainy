package com.brainy.data.controller;

import com.brainy.data.domain.BrainyUser;
import com.brainy.data.service.BrainyUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final Map<String, String> refreshTokenStore = new ConcurrentHashMap<>();

    @Autowired
    private BrainyUserService brainyService;

    // Endpoint per verificare l'utente autenticato
    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal OidcUser user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autenticato");
        }

        String email = user.getEmail();
        BrainyUser registeredUser = brainyService.findUserByEmail(email);

        if (registeredUser == null || !registeredUser.isEnabled()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accesso negato");
        }

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        // Verifica il Refresh Token
        String email = refreshTokenStore.get(refreshToken);

        BrainyUser user = brainyService.findByRereshToken(refreshToken);

        if (email == null || user== null || !email.equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Refresh Token non valido"));
        }

        // Genera un nuovo JWT
        String jwt = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 ora
                .signWith(SignatureAlgorithm.HS256, "TUO_SECRET_KEY".getBytes())
                .compact();

        brainyService.updateRefreshToken(jwt, email);

        return ResponseEntity.ok(Map.of("token", jwt));
    }

}
