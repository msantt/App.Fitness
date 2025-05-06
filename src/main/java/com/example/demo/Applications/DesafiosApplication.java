package com.example.demo.Applications;

import com.example.demo.Entities.Desafios;
import com.example.demo.Enum.Status;
import com.example.demo.Interfaces.IDesafios;
import com.example.demo.Repositories.DesafiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesafiosApplication implements IDesafios {


    private DesafiosRepository desafiosRepository;

    @Autowired
    public DesafiosApplication(DesafiosRepository desafiosRepository) {
        this.desafiosRepository = desafiosRepository;
    }

    @Override
    public Desafios salvar(Desafios desafio) {
        return desafiosRepository.save(desafio);
    }

    @Override
    public Optional<Desafios> buscarPorId(int id) {
        return desafiosRepository.findById(id);
    }

    @Override
    public List<Desafios> listarTodos() {
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
    public List<Desafios> buscarPorIdGrupo(int idGrupo) {
        return desafiosRepository.findByGrupos_Id(idGrupo);
    }

    @Override
    public List<Desafios> buscarPorIdCategoria(int idCategoria) {
        return desafiosRepository.findByCategoria_Id(idCategoria);
    }

    @Override
    public List<Desafios> buscarPorStatus(Status status) {
        return desafiosRepository.findByStatus(status);
    }
}
