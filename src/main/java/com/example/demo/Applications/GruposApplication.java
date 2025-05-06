package com.example.demo.Applications;

import com.example.demo.Entities.Grupos;
import com.example.demo.Enum.Status;
import com.example.demo.Interfaces.IGrupos;
import com.example.demo.Repositories.GruposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruposApplication implements IGrupos {


    private GruposRepository gruposRepository;

    @Autowired
    public GruposApplication(GruposRepository gruposRepository) {
        this.gruposRepository = gruposRepository;
    }

    @Override
    public Grupos salvar(Grupos grupo) {
        return gruposRepository.save(grupo);
    }

    @Override
    public Optional<Grupos> buscarPorId(int id) {
        return gruposRepository.findById(id);
    }

    @Override
    public List<Grupos> listarTodos() {
        return gruposRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        gruposRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return gruposRepository.existsById(id);
    }

    @Override
    public List<Grupos> buscarPorStatus(Status status) {
        return gruposRepository.findByStatus(status);
    }

    @Override
    public List<Grupos> buscarPorCriadorId(int criador) {
        return gruposRepository.findByCriadorId(criador);
    }

    @Override
    public List<Grupos> buscarPorNome(String nome) {
        return gruposRepository.findByNome(nome);
    }

}
