package com.example.demo.facades;

import com.example.demo.applications.MembrosDesafiosApplication;
import com.example.demo.entities.MembrosDesafio;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public MembrosDesafio buscar(int id) {
        return membrosDesafiosApplication.buscarPorId(id);
    }

    public void remover(int id) {
        membrosDesafiosApplication.deletar(id);
    }

    public List<MembrosDesafio> buscarPorDesafio(int desafioId) {
        return membrosDesafiosApplication.buscarPorDesafioId(desafioId);
    }
}
