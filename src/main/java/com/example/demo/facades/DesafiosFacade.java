package com.example.demo.facades;

import com.example.demo.applications.DesafiosApplication;
import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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


    public Desafio buscarPorId(int id) {
        return desafiosApplication.buscarPorId(id);
    }


    public List<Desafio> listarTodos() {
        return desafiosApplication.listarTodos();
    }


    public void deletar(int id) {
        desafiosApplication.deletar(id);
    }


    public boolean existePorId(int id) {
        return desafiosApplication.existePorId(id);
    }


    public List<Desafio> buscarPorIdGrupo(int idGrupo) {
        return desafiosApplication.buscarPorIdGrupo(idGrupo);
    }


    public List<Desafio> buscarPorIdCategoria(int idCategoria) {
        return desafiosApplication.buscarPorIdCategoria(idCategoria);
    }


    public List<Desafio> buscarPorStatus(Status status) {
        return desafiosApplication.buscarPorStatus(status);
    }
}
