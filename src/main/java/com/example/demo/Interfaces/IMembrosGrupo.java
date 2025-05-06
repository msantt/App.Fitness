package com.example.demo.Interfaces;

import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Enum.Status;

import java.util.List;
import java.util.Optional;

public interface IMembrosGrupo {

    MembrosGrupo salvar(MembrosGrupo membrosGrupo);

    Optional<MembrosGrupo> buscarPorId(int id);

    List<MembrosGrupo> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<MembrosGrupo> buscarPorGrupo(int grupoId);

    List<MembrosGrupo> buscarPorUsuario(int usuarioId);

    List<MembrosGrupo> buscarPorStatus(Status status);

    MembrosGrupo buscarPorGrupoEUsuario(Grupos grupo, Usuarios usuario);

    List<MembrosGrupo> buscarPorRole(String role);
}
