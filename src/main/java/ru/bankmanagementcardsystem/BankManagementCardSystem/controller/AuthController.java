package ru.bankmanagementcardsystem.BankManagementCardSystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.RegistredRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getRegistred(@Valid @RequestBody RegistredRequest registredRequest) {
        if (authService.getRegistred(registredRequest)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@Valid @RequestBody LoginRequest loginRequest) {
        if (authService.getLogin(loginRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
