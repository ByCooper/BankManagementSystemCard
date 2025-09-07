package ru.bankmanagementcardsystem.BankManagementCardSystem.dto;

import jakarta.validation.constraints.NotBlank;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Role;

import java.util.Set;

public class RegistredRequest {


    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Set<Role> roleName;

    public RegistredRequest() {
    }

    public RegistredRequest(String username, String password, Set<Role> roleName) {
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleName() {
        return roleName;
    }

    public void setRoleName(Set<Role> roleName) {
        this.roleName = roleName;
    }
}
