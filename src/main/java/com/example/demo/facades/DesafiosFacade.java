package com.example.demo.facades;

import com.example.demo.applications.DesafiosApplication;
import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DesafiosFacade {
    DesafiosApplication desafiosApplication;

    @Autowired
    public DesafiosFacade(DesafiosApplication desafiosApplication) {
        this.desafiosApplication = desafiosApplication;
    }


    public Desafio salvar(Desafio desafio) {
        return desafiosApplication.salvar(desafio);
    }


    public Desafio buscarPorId(UUID id) {
        return desafiosApplication.buscarPorUUID(id);
    }


    public List<Desafio> listarTodos() {
        return desafiosApplication.listarTodos();
    }


    public void deletar(UUID id) {
        desafiosApplication.deletar(id);
    }


    public boolean existePorUUID(UUID id) {
        return desafiosApplication.existePorUUID(id);
    }


    public List<Desafio> buscarPorIdGrupo(UUID idGrupo) {
        return desafiosApplication.buscarPorUUIDGrupo(idGrupo);
    }


    public List<Desafio> buscarPorIdCategoria(UUID idCategoria) {
        return desafiosApplication.buscarPorUUIDCategoria(idCategoria);
    }


    public List<Desafio> buscarPorStatus(Status status) {
        return desafiosApplication.buscarPorStatus(status);
    }

    public Desafio buscarPorCodigo(String codigo) {
        return desafiosApplication.buscarPorCodigo(codigo);
    }

    public boolean cancelarDesafio(UUID desafioId, UUID usuarioId) {
        return desafiosApplication.cancelarDesafio(desafioId, usuarioId);
    }
}
