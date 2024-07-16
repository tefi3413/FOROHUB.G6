package com.example.challenge.ForoHub.challenge.ForoHub.repository;

import com.example.challenge.ForoHub.challenge.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}