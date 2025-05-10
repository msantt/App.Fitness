package com.example.demo.repositories;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembrosGrupoRepository extends JpaRepository<MembrosGrupo, Integer> {

    List<MembrosGrupo> findByGrupo_Id(int grupoId);

    List<MembrosGrupo> findByUsuario_Id(int usuarioId);

    List<MembrosGrupo> findByStatus(Status status);

    MembrosGrupo findByGrupoAndUsuario(Grupo grupo, Usuario usuario);

    List<MembrosGrupo> findByRole(String role);
}
