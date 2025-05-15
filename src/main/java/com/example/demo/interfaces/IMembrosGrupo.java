package com.example.demo.interfaces;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface IMembrosGrupo {

    MembrosGrupo salvar(MembrosGrupo membrosGrupo);

    MembrosGrupo buscarPorId(int id);

    List<MembrosGrupo> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<MembrosGrupo> buscarPorGrupo(int grupoId);

    List<MembrosGrupo> buscarPorUsuario(int usuarioId);

    List<MembrosGrupo> buscarPorStatus(Status status);

    MembrosGrupo buscarPorGrupoEUsuario(Grupo grupo, Usuario usuario);

    List<MembrosGrupo> buscarPorRole(TipoUsuario role);
}
