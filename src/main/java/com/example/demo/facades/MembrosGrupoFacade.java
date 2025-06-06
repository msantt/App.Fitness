package com.example.demo.facades;

import com.example.demo.applications.MembrosGrupoApplication;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MembrosGrupoFacade {

    MembrosGrupoApplication membrosGrupoApplication;
    @Autowired
    public MembrosGrupoFacade(MembrosGrupoApplication membrosGrupoApplication) {
        this.membrosGrupoApplication = membrosGrupoApplication;
    }


    public MembrosGrupo salvar(MembrosGrupo membrosGrupo) {
        return membrosGrupoApplication.salvar(membrosGrupo);
    }

    public MembrosGrupo buscarPorUUID(UUID id) {
        return membrosGrupoApplication.buscarPorUUID(id);
    }

    public List<MembrosGrupo> listarTodos() {
        return membrosGrupoApplication.listarTodos();
    }

    public void deletar(UUID id) {
        membrosGrupoApplication.deletar(id);
    }

    public boolean existePorUUID(UUID id) {
        return membrosGrupoApplication.existePorUUID(id);
    }

    public List<MembrosGrupo> buscarPorGrupo(UUID grupoId) {
        return membrosGrupoApplication.buscarPorGrupo( grupoId);
    }

    public List<MembrosGrupo> buscarPorUsuario(UUID usuarioId) {
        return membrosGrupoApplication.buscarPorUsuario(usuarioId);
    }

    public List<MembrosGrupo> buscarPorStatus(Status status) {
        return membrosGrupoApplication.buscarPorStatus(status);
    }

    public MembrosGrupo buscarPorGrupoEUsuario(Grupo grupo, Usuario usuario) {
        return membrosGrupoApplication.buscarPorGrupoEUsuario(grupo, usuario);
    }

    public List<MembrosGrupo> buscarPorRole(TipoUsuario role) {
        return membrosGrupoApplication.buscarPorRole(role);
    }

}
