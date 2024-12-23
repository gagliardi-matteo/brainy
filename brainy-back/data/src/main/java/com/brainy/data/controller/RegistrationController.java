package com.brainy.data.controller;

import com.brainy.data.domain.BrainyUser;
import com.brainy.data.service.BrainyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200") // Modifica l'origine per il tuo dominio
public class RegistrationController {

    @Autowired
    private BrainyUserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody BrainyUser user) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("L'utente con questa email è già registrato.");
        }

        BrainyUser registeredUser = userService.registerUser(user.getEmail(), user.getName());
        return ResponseEntity.ok(registeredUser);
    }
}
