package ru.bankmanagementcardsystem.BankManagementCardSystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getCreateUser(@Valid @RequestBody LoginRequest loginRequest){
        authService.getCreateUser(loginRequest);
        return ResponseEntity.ok().build();
    }
}
