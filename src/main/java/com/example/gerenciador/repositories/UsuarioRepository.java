package com.example.gerenciador.repositories;

import com.example.gerenciador.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("from Usuario where password = :password and email = :email")
    Optional<Usuario> findUsuarioByEmailAndPassword(@Param("email")String email, @Param("password")String password);
}
