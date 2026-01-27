package com.apiresttask.apiTasks.DTO;

import com.apiresttask.apiTasks.Entity.User.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResquest {

    @NotBlank
    @Size(min = 5, max = 30)
    private String nome;
    @NotNull
    @Size(min = 5, max = 30)
    private String email;
    @NotNull
    @Size(min = 5, max = 12)
    private String password;
    @NotNull
    private UserRole roles;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRoles() {
        return roles;
    }

    public void setRoles(UserRole roles) {
        this.roles = roles;
    }
}
