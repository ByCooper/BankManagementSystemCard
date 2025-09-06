package ru.bankmanagementcardsystem.BankManagementCardSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.User;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.RoleRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.UserRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void getCreateUser(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = passwordEncoder.encode(loginRequest.getPassword());

        User user = User.builder()
                .name(username)
                .password(password)
                .build();
        userRepository.save(user);
    }
}
