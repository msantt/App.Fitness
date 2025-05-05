package com.example.demo.Facades;

import com.example.demo.Applications.GruposApplication;
import com.example.demo.Entities.Grupos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GruposFacede {

    GruposApplication gruposApplication;
    @Autowired
    public GruposFacede(GruposApplication gruposApplication) {
        this.gruposApplication = gruposApplication;
    }


    public Grupos salvar(Grupos grupo) {
        return gruposApplication.salvar(grupo);
    }


    public Optional<Grupos> buscarPorId(int id) {
        return gruposApplication.buscarPorId(id);
    }


    public List<Grupos> listarTodos() {
        return gruposApplication.listarTodos();
    }


    public void deletar(int id) {
        gruposApplication.deletar(id);
    }


    public boolean existePorId(int id) {
        return gruposApplication.existePorId(id);
    }


    public List<Grupos> buscarPorStatus(boolean status) {
        return gruposApplication.buscarPorStatus(status);
    }


    public List<Grupos> buscarPorCriadorId(int criador) {
        return gruposApplication.buscarPorCriadorId(criador);
    }


    public List<Grupos> buscarPorNome(String nome) {
        return gruposApplication.buscarPorNome(nome);
    }
}
