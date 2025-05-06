package com.example.demo.Facades;

import com.example.demo.Applications.MembrosGrupoApplication;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MembrosGrupoFacede {

    MembrosGrupoApplication membrosGrupoApplication;
    @Autowired
    public MembrosGrupoFacede(MembrosGrupoApplication membrosGrupoApplication) {
        this.membrosGrupoApplication = membrosGrupoApplication;
    }


    public MembrosGrupo salvar(MembrosGrupo membrosGrupo) {
        return membrosGrupoApplication.salvar(membrosGrupo);
    }

    public Optional<MembrosGrupo> buscarPorId(int id) {
        return membrosGrupoApplication.buscarPorId(id);
    }

    public List<MembrosGrupo> listarTodos() {
        return membrosGrupoApplication.listarTodos();
    }

    public void deletar(int id) {
        membrosGrupoApplication.deletar(id);
    }

    public boolean existePorId(int id) {
        return membrosGrupoApplication.existePorId(id);
    }

    public List<MembrosGrupo> buscarPorGrupo(int grupoId) {
        return membrosGrupoApplication.buscarPorGrupo( grupoId);
    }

    public List<MembrosGrupo> buscarPorUsuario(int usuarioId) {
        return membrosGrupoApplication.buscarPorUsuario(usuarioId);
    }

    public List<MembrosGrupo> buscarPorStatus(Status status) {
        return membrosGrupoApplication.buscarPorStatus(status);
    }

    public MembrosGrupo buscarPorGrupoEUsuario(Grupos grupo, Usuarios usuario) {
        return membrosGrupoApplication.buscarPorGrupoEUsuario(grupo, usuario);
    }

    public List<MembrosGrupo> buscarPorRole(String role) {
        return membrosGrupoApplication.buscarPorRole(role);
    }

}
