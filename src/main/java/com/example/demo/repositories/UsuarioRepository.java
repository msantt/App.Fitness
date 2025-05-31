package com.example.demo.repositories;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);

    UserDetails findByEmail(String email);

    List<Usuario> findByStatusTrue();

    boolean existsByEmail(String email);

    @Query("SELECT g FROM Grupo g JOIN MembrosGrupo m ON g.uuid = m.grupo.uuid WHERE m.usuario.uuid = :idUsuario")
    List<Grupo> findGruposPorUsuarioUuid(@Param("idUsuario") UUID idUsuario);

    @Query("""
    SELECT md.desafio
    FROM MembrosDesafio md
    WHERE md.usuario.objetivo = (
        SELECT u.objetivo FROM Usuario u WHERE u.uuid = :usuarioUUID
    )
    AND md.desafio NOT IN (
        SELECT md2.desafio FROM MembrosDesafio md2 WHERE md2.usuario.uuid = :usuarioUUID
    )
    GROUP BY md.desafio
    ORDER BY COUNT(md.desafio) DESC
""")
    Page<Desafio> recomendarDesafiosMaisPopularesPorObjetivo(@Param("usuarioUUID") UUID usuarioUUID, Pageable pageable);

}
