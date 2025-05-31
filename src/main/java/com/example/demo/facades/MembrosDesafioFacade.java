package com.example.demo.facades;

import com.example.demo.applications.MembrosDesafiosApplication;
import com.example.demo.entities.MembrosDesafio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MembrosDesafioFacade {

    private final MembrosDesafiosApplication membrosDesafiosApplication;

    public MembrosDesafioFacade(MembrosDesafiosApplication membrosDesafiosApplication) {
        this.membrosDesafiosApplication = membrosDesafiosApplication;
    }

    public MembrosDesafio criar(MembrosDesafio membroDesafio) {
        return membrosDesafiosApplication.salvar(membroDesafio);
    }

    public List<MembrosDesafio> listar() {
        return membrosDesafiosApplication.listarTodos();
    }

    public MembrosDesafio buscar(UUID id) {
        return membrosDesafiosApplication.buscarPorUUID(id);
    }

    public void remover(UUID id) {
        membrosDesafiosApplication.deletar(id);
    }

    public List<MembrosDesafio> buscarPorDesafio(UUID desafioId) {
        return membrosDesafiosApplication.buscarPorDesafioUUID(desafioId);
    }
}
