package com.example.demo.facades;

import com.example.demo.applications.RecompensaApplication;
import com.example.demo.entities.Recompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecompensaFacade {

    private final RecompensaApplication recompensaApplication;

    @Autowired
    public RecompensaFacade(RecompensaApplication recompensaApplication) {
        this.recompensaApplication = recompensaApplication;
    }

    public Recompensa salvarRecompensa(Recompensa recompensa) {
        return recompensaApplication.salvar(recompensa);
    }

    public Recompensa buscarRecompensaPorId(int id) {
        return recompensaApplication.buscarPorId(id);
    }

    public List<Recompensa> listarRecompensas() {
        return recompensaApplication.listarTodos();
    }

    public void deletarRecompensa(int id) {
        recompensaApplication.deletar(id);
    }

    public boolean existeRecompensaPorId(int id) {
        return recompensaApplication.existePorId(id);
    }

    public List<Recompensa> buscarRecompensasPorUsuario(int idUsuario) {
        return recompensaApplication.buscarPorId(idUsuario);
    }

    public List<Recompensa> buscarRecompensasPorDesafio(int idDesafio) {
        return recompensaApplication.buscarPorId(idDesafio);
    }
}
