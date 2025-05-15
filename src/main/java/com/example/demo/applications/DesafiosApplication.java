package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IDesafios;
import com.example.demo.repositories.DesafioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (desafio.getDataInicio().isBefore(LocalDate.now())) {
            throw new RegraNegocioException("A data de início deve ser futura.");
        }

        if (desafio.getDataFim().isBefore(desafio.getDataInicio().plusDays(3))) {
            throw new RegraNegocioException("O desafio deve ter no mínimo 3 dias de duração.");
        }

        if (desafio.getGrupos() == null) {
            throw new RegraNegocioException("O desafio deve estar associado a um grupo.");
        }
        if (desafio.getGrupos().status() != Status.ATIVO) {
            throw new RegraNegocioException("O grupo do desafio deve estar ativo.");
        }

        if (desafio.getCategoria() == null) {
            throw new RegraNegocioException("O desafio deve ter uma categoria.");
        }

        boolean nomeRepetido = existePorNome(desafio.getNome(),desafio);

        if (nomeRepetido) {
            throw new RegraNegocioException("Já existe um desafio com esse nome no grupo.");
        }
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

    public boolean existePorNome(String nome, Desafio desafio) {
        return desafiosRepository
                .findByGrupo_Id(desafio.getGrupos().id())
                .stream()
                .anyMatch(d -> d.getNome().equalsIgnoreCase(desafio.getNome()));
    }
}
