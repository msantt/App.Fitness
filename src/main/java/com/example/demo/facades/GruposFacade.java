package com.example.demo.facades;

import com.example.demo.applications.GruposApplication;
import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GruposFacade {

    GruposApplication gruposApplication;
    @Autowired
    public GruposFacade(GruposApplication gruposApplication) {
        this.gruposApplication = gruposApplication;
    }


    public Grupo salvar(Grupo grupo) {
        return gruposApplication.salvar(grupo);
    }


    public Grupo buscarPorId(int id) {
        return gruposApplication.buscarPorId(id);
    }


    public List<Grupo> listarTodos() {
        return gruposApplication.listarTodos();
    }


    public void deletar(int id) {
        gruposApplication.deletar(id);
    }


    public boolean existePorId(int id) {
        return gruposApplication.existePorId(id);
    }


    public List<Grupo> buscarPorStatus(Status status) {
        return gruposApplication.buscarPorStatus(status);
    }


    public List<Grupo> buscarPorCriadorId(int criador) {
        return gruposApplication.buscarPorCriadorId(criador);
    }


    public List<Grupo> buscarPorNome(String nome) {
        return gruposApplication.buscarPorNome(nome);
    }
}
