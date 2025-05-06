package com.example.demo.Repositories;

import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembrosGrupoRepository extends JpaRepository<MembrosGrupo, Integer> {

    List<MembrosGrupo> findByGrupo_Id(int grupoId);

    List<MembrosGrupo> findByUsuario_Id(int usuarioId);

    List<MembrosGrupo> findByStatus(Status status);

    MembrosGrupo findByGrupoAndUsuario(Grupos grupo, Usuarios usuario);

    List<MembrosGrupo> findByRole(String role);
}
