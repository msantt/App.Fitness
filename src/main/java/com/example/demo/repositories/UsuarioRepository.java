package com.example.demo.repositories;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByStatusTrue();

    boolean existsByEmail(String email);

    @Query("SELECT g FROM Grupo g JOIN MembrosGrupo m ON g.id = m.grupo.id WHERE m.usuario.id = :idUsuario")
    List<Grupo> findGruposPorUsuarioId(@Param("idUsuario") int idUsuario);

    @Query("""
    SELECT md.desafio
    FROM MembrosDesafio md
    WHERE md.usuario.objetivo = (
        SELECT u.objetivo FROM Usuario u WHERE u.id = :usuarioId
    )
    AND md.desafio NOT IN (
        SELECT md2.desafio FROM MembrosDesafio md2 WHERE md2.usuario.id = :usuarioId
    )
    GROUP BY md.desafio
    ORDER BY COUNT(md.desafio) DESC
""")
    Page<Desafio> recomendarDesafiosMaisPopularesPorObjetivo(@Param("usuarioId") int usuarioId, Pageable pageable);

}
