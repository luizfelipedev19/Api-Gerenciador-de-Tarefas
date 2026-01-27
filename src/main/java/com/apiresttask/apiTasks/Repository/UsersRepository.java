package com.apiresttask.apiTasks.Repository;

import com.apiresttask.apiTasks.Entity.User.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository <Usuarios, Long> {
        UserDetails findByEmail(String email);

}
