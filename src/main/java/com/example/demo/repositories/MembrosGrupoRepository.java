package com.example.demo.repositories;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MembrosGrupoRepository extends JpaRepository<MembrosGrupo, UUID> {

    List<MembrosGrupo> findByGrupo_Uuid(UUID grupoUUID);

    List<MembrosGrupo> findByUsuario_Uuid(UUID usuarioUUID);

    List<MembrosGrupo> findByStatus(Status status);

    MembrosGrupo findByGrupoAndUsuario(Grupo grupo, Usuario usuario);

    List<MembrosGrupo> findByRole(TipoUsuario role);
    boolean existsByGrupo_UuidAndUsuario_Uuid(UUID grupoUUID, UUID usuarioUUID);

    void deleteByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    MembrosGrupo findByUuid(UUID uuid);

    boolean existsByGrupoUuidAndUsuarioIdAndRole(UUID grupoOuDesafioId, UUID usuarioId, TipoUsuario tipoUsuario);
}
