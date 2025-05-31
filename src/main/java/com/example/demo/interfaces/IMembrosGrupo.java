package com.example.demo.interfaces;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;

import java.util.List;
import java.util.UUID;

public interface IMembrosGrupo {

    MembrosGrupo salvar(MembrosGrupo membrosGrupo);

    MembrosGrupo buscarPorUUID(UUID id);

    List<MembrosGrupo> listarTodos();

    void deletar(UUID id);

    boolean existePorUUID(UUID id);

    List<MembrosGrupo> buscarPorGrupo(UUID grupoUUID);

    List<MembrosGrupo> buscarPorUsuario(UUID usuarioUUID);

    List<MembrosGrupo> buscarPorStatus(Status status);

    MembrosGrupo buscarPorGrupoEUsuario(Grupo grupo, Usuario usuario);

    List<MembrosGrupo> buscarPorRole(TipoUsuario role);
}
