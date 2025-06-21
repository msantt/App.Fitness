package com.example.demo.facades;

import com.example.demo.applications.UsuariosApplication;
import com.example.demo.entities.CheckIn;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    public Usuario atualizar(Usuario usuario) {
        return usuariosApplication.update(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuariosApplication.buscarPorUUID(id);
    }


    public Usuario buscarPorEmail(String email) {
        return usuariosApplication.buscarPorEmail(email);
    }


    public List<Usuario> listarTodos() {
        return usuariosApplication.listarTodos();
    }


    public void deletar(UUID id) {
        usuariosApplication.deletar(id);
    }


    public boolean existePorEmail(String email) {
        return usuariosApplication.existePorEmail(email);
    }


    public boolean existePorId(UUID id) {
        return usuariosApplication.existePorUUID(id);
    }


    public List<Grupo> listarGruposPorUsuarioId(UUID idUsuario) {
        return usuariosApplication.listarGruposPorUsuarioUUID(idUsuario);
    }


    public List<Usuario> buscarAtivos() {
        return usuariosApplication.buscarAtivos();
    }

    public List<Desafio> recomendarDesafiosPopulares(UUID usuarioId) {
        return usuariosApplication.recomendarDesafiosPopulares(usuarioId);
    }

    public BigDecimal sacar(UUID idUsuario, BigDecimal valor) {
        return usuariosApplication.sacar(idUsuario,valor);
    }

    public BigDecimal depositar(UUID idUsuario, BigDecimal valor) {
        return usuariosApplication.depositar(idUsuario,valor);
    }

}
