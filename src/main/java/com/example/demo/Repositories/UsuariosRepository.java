package com.example.demo.Repositories;
import com.example.demo.Entities.CheckIn;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByEmail(String email);

    List<Usuarios> findByStatusTrue();

    boolean existsByEmail(String email);

    @Query("SELECT g FROM Grupos g JOIN MembrosGrupo m ON g.id = m.grupo.id WHERE m.usuario.id = :idUsuario")
    List<Grupos> findGruposPorUsuarioId(@Param("idUsuario") int idUsuario);

    @Query("SELECT c FROM CheckIn c WHERE c.usuario.id = :idUsuario")
    List<CheckIn> findCheckinsPorUsuarioId(@Param("idUsuario") int idUsuario);



}
