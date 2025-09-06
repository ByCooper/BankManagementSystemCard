package ru.bankmanagementcardsystem.BankManagementCardSystem.service;

import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;

public interface AuthService {
    void getCreateUser(LoginRequest loginRequest);
}
