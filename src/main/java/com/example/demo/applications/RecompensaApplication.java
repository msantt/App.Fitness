package com.example.demo.applications;

import com.example.demo.entities.Recompensa;
import com.example.demo.interfaces.IRecompensa;
import com.example.demo.repositories.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecompensaApplication implements IRecompensa {

    private final RecompensaRepository recompensaRepository;

    @Autowired
    public RecompensaApplication(RecompensaRepository recompensaRepository) {
        this.recompensaRepository = recompensaRepository;
    }

    @Override
    public Recompensa salvar(Recompensa recompensa) {
        return recompensaRepository.save(recompensa);
    }

    @Override
    public Recompensa buscarPorId(UUID id) {
        return recompensaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Recompensa> listarTodos() {
        return recompensaRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        recompensaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(UUID id) {
        return recompensaRepository.existsById(id);
    }

    @Override
    public List<Recompensa> listarPorMembroDesafioId(UUID idDesafio) {
        return recompensaRepository.findByMembroDesafioId(idDesafio);
    }
}

