package com.example.demo.applications;

import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IDesafios;
import com.example.demo.repositories.DesafioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesafiosApplication implements IDesafios {


    private DesafioRepository desafiosRepository;

    @Autowired
    public DesafiosApplication(DesafioRepository desafiosRepository) {
        this.desafiosRepository = desafiosRepository;
    }

    @Override
    public Desafio salvar(Desafio desafio) {
        return desafiosRepository.save(desafio);
    }

    @Override
    public Desafio buscarPorId(int id) {
        return desafiosRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Desafio> listarTodos() {
        return desafiosRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        desafiosRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return desafiosRepository.existsById(id);
    }

    @Override
    public List<Desafio> buscarPorIdGrupo(int idGrupo) {
        return desafiosRepository.findByGrupo_Id(idGrupo);
    }

    @Override
    public List<Desafio> buscarPorIdCategoria(int idCategoria) {
        return desafiosRepository.findByCategoria_Id(idCategoria);
    }

    @Override
    public List<Desafio> buscarPorStatus(Status status) {
        return desafiosRepository.findByStatus(status);
    }
}
