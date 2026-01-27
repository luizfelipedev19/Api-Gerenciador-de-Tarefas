package com.apiresttask.apiTasks.Controler;

import com.apiresttask.apiTasks.Entity.User.AuthenticationDados;
import com.apiresttask.apiTasks.Entity.User.LoginResponseDTO;
import com.apiresttask.apiTasks.Entity.User.RegisterDados;
import com.apiresttask.apiTasks.Entity.User.Usuarios;
import com.apiresttask.apiTasks.Repository.UsersRepository;
import com.apiresttask.apiTasks.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDados data) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        data.email(),
                        data.password()
                );

       var auth = this.authenticationManager.authenticate(usernamePassword);

       //gerar token
        var token = tokenService.generateToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDados data) {

        if (repository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        Usuarios newUser = new Usuarios(
                data.email(),
                encryptedPassword,
                data.role() // deve ser ROLE_ADMIN ou ROLE_USER
        );

        repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
