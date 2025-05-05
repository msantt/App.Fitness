package com.example.demo.Facades;

import com.example.demo.Applications.UsuariosApplication;
import com.example.demo.Entities.CheckIn;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuariosFacade {

    UsuariosApplication usuariosApplication;

    @Autowired
    public UsuariosFacade(UsuariosApplication usuariosApplication) {
        this.usuariosApplication = usuariosApplication;
    }

    public Usuarios salvar(Usuarios usuario) {
        return usuariosApplication.salvar(usuario);
    }


    public Optional<Usuarios> buscarPorId(int id) {
        return usuariosApplication.buscarPorId(id);
    }


    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuariosApplication.buscarPorEmail(email);
    }


    public List<Usuarios> listarTodos() {
        return usuariosApplication.listarTodos();
    }


    public void deletar(int id) {
        usuariosApplication.deletar(id);
    }


    public boolean existePorEmail(String email) {
        return usuariosApplication.existePorEmail(email);
    }


    public boolean existePorId(int id) {
        return usuariosApplication.existePorId(id);
    }


    public List<Grupos> listarGruposPorUsuarioId(int idUsuario) {
        return usuariosApplication.listarGruposPorUsuarioId(idUsuario);
    }


    public List<CheckIn> listarCheckinsPorUsuarioId(int idUsuario) {
        return usuariosApplication.listarCheckinsPorUsuarioId(idUsuario);
    }


    public List<Usuarios> buscarAtivos() {
        return usuariosApplication.buscarAtivos();
    }

}
