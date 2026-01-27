package com.apiresttask.apiTasks.infra.security;


import com.apiresttask.apiTasks.Repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

//O Once, garante que o filtro rode uma única vez
public class SecurityFilter extends OncePerRequestFilter {
    /**
     * Esses são os objetos:
     * HttpServletRequest → requisição HTTP
     * HttpServletResponse → resposta
     * FilterChain → cadeia de filtros
     */

    /**
     * UserDetails → representação do usuário no Spring Security
     * UsernamePasswordAuthenticationToken → objeto de autenticação
     * SecurityContextHolder → contexto global de segurança da requisição
     */
    @Autowired
            //Valida o token
    TokenService tokenService;
    @Autowired

            //busca o usuário no banco
    UsersRepository usersRepository;
    @Override
    protected void doFilterInternal(
            //chamado toda vez que chega uma requisição
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //Pega o header Authorization e extrai o JWT
        var token = this.recoverToken(request);

        //verifica se existe token
        if (token != null){
          var subject = tokenService.validateToken(token);

          //aqui eu busco o usuário no banco. Retorna um details que contem todos os dados
            UserDetails user = usersRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //Passa a requisição pra frente
        filterChain.doFilter(request, response);
    }

    //função responsável por extrair o token
    public String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer", "");

    }
}
