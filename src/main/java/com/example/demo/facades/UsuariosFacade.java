package com.example.demo.facades;

import com.example.demo.applications.UsuariosApplication;
import com.example.demo.entities.CheckIn;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuariosFacade {

    UsuariosApplication usuariosApplication;

    @Autowired
    public UsuariosFacade(UsuariosApplication usuariosApplication) {
        this.usuariosApplication = usuariosApplication;
    }

    public Usuario salvar(Usuario usuario) {
        return usuariosApplication.salvar(usuario);
    }


    public Usuario buscarPorId(int id) {
        return usuariosApplication.buscarPorId(id);
    }


    public Usuario buscarPorEmail(String email) {
        return usuariosApplication.buscarPorEmail(email);
    }


    public List<Usuario> listarTodos() {
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


    public List<Grupo> listarGruposPorUsuarioId(int idUsuario) {
        return usuariosApplication.listarGruposPorUsuarioId(idUsuario);
    }


    public List<Usuario> buscarAtivos() {
        return usuariosApplication.buscarAtivos();
    }

    public List<Desafio> recomendarDesafiosPopulares(int usuarioId) {
        return usuariosApplication.recomendarDesafiosPopulares(usuarioId);
    }

}
