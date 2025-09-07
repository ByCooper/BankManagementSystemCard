package ru.bankmanagementcardsystem.BankManagementCardSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.LoginRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.dto.RegistredRequest;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.RoleRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.UserRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.AuthService;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean getRegistred(RegistredRequest registredrequest){
        if(userDetailsManager.userExists(registredrequest.getUsername())){
            return false;
        }

        userDetailsManager.createUser(
                User.builder()
                        .passwordEncoder(this.passwordEncoder::encode)
                        .password(registredrequest.getPassword())
                        .username(registredrequest.getUsername())
                        .roles(registredrequest.getRoleName().toString())
                        .build());
        return true;
    }

    @Override
    public boolean getLogin(LoginRequest loginRequest){
        if(!userDetailsManager.userExists(loginRequest.getUsername())){
            return false;
        }
        UserDetails userDetails = userDetailsManager.loadUserByUsername(loginRequest.getUsername());
        return passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword());
    }


}
