package com.apiresttask.apiTasks.Service;

import com.apiresttask.apiTasks.DTO.TaskResponse;
import com.apiresttask.apiTasks.DTO.UsuarioResponse;
import com.apiresttask.apiTasks.DTO.UsuarioResquest;
import com.apiresttask.apiTasks.Entity.Tarefas.Tasks;
import com.apiresttask.apiTasks.Entity.User.Usuarios;
import com.apiresttask.apiTasks.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsuariosService implements UserDetailsService {


    @Autowired
    UsersRepository usersRepository;


    public Usuarios toEntity(UsuarioResquest resquest) {
        Usuarios user = new Usuarios();

        user.setNome(resquest.getNome());
        user.setEmail(resquest.getEmail());
        user.setPassword(resquest.getPassword());
        return user;
    }

    public UsuarioResponse toResponse(Usuarios user) {
        UsuarioResponse response = new UsuarioResponse();

        response.setId(user.getId());
        response.setNome(user.getNome());
        response.setEmail(user.getEmail());

        return response;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username);
    }
}
