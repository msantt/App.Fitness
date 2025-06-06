package com.example.demo.facades;

import com.example.demo.applications.GruposApplication;
import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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


    public Grupo buscarPorUUID(UUID id) {
        return gruposApplication.buscarPorUUID(id);
    }


    public List<Grupo> listarTodos() {
        return gruposApplication.listarTodos();
    }


    public void deletar(UUID id) {
        gruposApplication.deletar(id);
    }


    public boolean existePorUUID(UUID id) {
        return gruposApplication.existePorUUID(id);
    }


    public List<Grupo> buscarPorStatus(Status status) {
        return gruposApplication.buscarPorStatus(status);
    }


    public List<Grupo> buscarPorCriadorUUID(UUID criador) {
        return gruposApplication.buscarPorCriadorUUID(criador);
    }


    public List<Grupo> buscarPorNome(String nome) {
        return gruposApplication.buscarPorNome(nome);
    }
}
