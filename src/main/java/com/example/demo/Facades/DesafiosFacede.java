package com.example.demo.Facades;

import com.example.demo.Applications.DesafiosApplication;
import com.example.demo.Entities.Desafios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DesafiosFacede {
    DesafiosApplication desafiosApplication;

    @Autowired
    public DesafiosFacede(DesafiosApplication desafiosApplication) {
        this.desafiosApplication = desafiosApplication;
    }


    public Desafios salvar(Desafios desafio) {
        return desafiosApplication.salvar(desafio);
    }


    public Optional<Desafios> buscarPorId(int id) {
        return desafiosApplication.buscarPorId(id);
    }


    public List<Desafios> listarTodos() {
        return desafiosApplication.listarTodos();
    }


    public void deletar(int id) {
        desafiosApplication.deletar(id);
    }


    public boolean existePorId(int id) {
        return desafiosApplication.existePorId(id);
    }


    public List<Desafios> buscarPorIdGrupo(int idGrupo) {
        return desafiosApplication.buscarPorIdGrupo(idGrupo);
    }


    public List<Desafios> buscarPorIdCategoria(int idCategoria) {
        return desafiosApplication.buscarPorIdCategoria(idCategoria);
    }


    public List<Desafios> buscarPorStatus(boolean status) {
        return desafiosApplication.buscarPorStatus(status);
    }
}
