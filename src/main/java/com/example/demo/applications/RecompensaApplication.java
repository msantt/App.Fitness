package com.example.demo.applications;

import com.example.demo.entities.Recompensa;
import com.example.demo.interfaces.IRecompensa;
import com.example.demo.repositories.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompensaApplication implements IRecompensa {

    private RecompensaRepository recompensaRepository;

    @Autowired
    public RecompensaApplication(RecompensaRepository recompensaRepository) {
        this.recompensaRepository = recompensaRepository;
    }

    @Override
    public Recompensa salvar(Recompensa recompensa) {
        return recompensaRepository.save(recompensa);
    }

    @Override
    public Recompensa buscarPorId(int id) {
        return recompensaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Recompensa> listarTodos() {
        return recompensaRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        recompensaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return recompensaRepository.findById(id).isPresent();
    }

    @Override
    public List<Recompensa> listarPorUsuarioId(int idUsuario) {
        return recompensaRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public List<Recompensa> listarPorDesafioId(int idDesafio) {
        return recompensaRepository.findByDesafioId(idDesafio);
    }
}
