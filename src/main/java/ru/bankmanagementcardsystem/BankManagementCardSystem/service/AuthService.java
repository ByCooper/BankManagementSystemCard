package ru.bankmanagementcardsystem.BankManagementCardSystem.service;

import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.RegistredRequest;

public interface AuthService {
    boolean getRegistred(RegistredRequest loginRequest);
    boolean getLogin(LoginRequest loginRequest);
}
