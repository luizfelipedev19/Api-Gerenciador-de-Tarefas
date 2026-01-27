package com.apiresttask.apiTasks.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//Essa anotação diz que essa classe configura bens
@Configuration
public class SecurityConfiguration {
    /**
     * HttpSecurity → configura regras de acesso
     * SecurityFilterChain → cadeia de filtros de segurança
     * AuthenticationManager → executa o login
     * PasswordEncoder → criptografia de senha
     * SessionCreationPolicy → política de sessão
     * UsernamePasswordAuthenticationFilter → filtro padrão de login
     */

    @Autowired
    /**
     * Aqui ele vai ler o JWT, validar o token, carregar o usuário
     * e colocar o usuário no SecurityContext
     */
    private SecurityFilter securityFilter;

    //Um Bean registra objetos no contexto Spring
    @Bean
    //essa função vai definir todas as regras de segurança e retornando uma cadeia de filtros
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                //csrf protege formulários baseados em sessão.
                .csrf(csrf -> csrf.disable())

                //aqui eu estou dizendo que não é para guardar sessão do usuário no servidor
                //se for usar o JWT (TOKEN), não pode guardar sessões. Cada intereção, deve ter seu token.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                //aqui eu defino quem pode acessar o que.
                //então se eu quiser liberar acessos para post, get, acessos externos, eu faço com este bloco.
                .authorizeHttpRequests(auth -> auth

                        // 🔓 Swagger
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 🔓 Auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // 🔒 Regras específicas
                        .requestMatchers(HttpMethod.POST, "/tarefas").hasRole("ADMIN")

                        // 🔒 Qualquer outra rota
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean

    //valida usuário + senha
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Aqui eu defino hash de senha. Nessa função, eu criptografo as senhas usando o BCrypt
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
