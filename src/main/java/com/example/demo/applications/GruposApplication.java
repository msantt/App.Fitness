package com.example.demo.applications;

import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IGrupos;
import com.example.demo.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruposApplication implements IGrupos {


    private GrupoRepository gruposRepository;

    @Autowired
    public GruposApplication(GrupoRepository gruposRepository) {
        this.gruposRepository = gruposRepository;
    }

    @Override
    public Grupo salvar(Grupo grupo) {
        return gruposRepository.save(grupo);
    }

    @Override
    public Grupo buscarPorId(int id) {
        return gruposRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Grupo> listarTodos() {
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
    public List<Grupo> buscarPorStatus(Status status) {
        return gruposRepository.findByStatus(status);
    }

    @Override
    public List<Grupo> buscarPorCriadorId(int criador) {
        return gruposRepository.findByCriadorId(criador);
    }

    @Override
    public List<Grupo> buscarPorNome(String nome) {
        return gruposRepository.findByNome(nome);
    }

}
