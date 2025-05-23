package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IDesafios;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.DesafioRepository;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.MembrosDesafioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DesafiosApplication implements IDesafios {


    private final GrupoRepository grupoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MembrosDesafioRepository membrosDesafioRepository;
    private DesafioRepository desafiosRepository;
    private UsuariosApplication usuarioRepository;

    @Autowired
    public DesafiosApplication(DesafioRepository desafiosRepository, GrupoRepository grupoRepository, CategoriaRepository categoriaRepository, UsuariosApplication usuarioRepository, MembrosDesafioRepository membrosDesafioRepository) {
        this.desafiosRepository = desafiosRepository;
        this.grupoRepository = grupoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.membrosDesafioRepository = membrosDesafioRepository;
    }

    @Override
    public Desafio salvar(Desafio desafio) {
        if (desafio.getStatus() == null) {
            desafio.setStatus(Status.ATIVO);
        }
        if (desafio.getDataInicio().isBefore(LocalDate.now())) {
            throw new RegraNegocioException("A data de início deve ser futura.");
        }

        if (desafio.getDataFim().isBefore(desafio.getDataInicio().plusDays(3))) {
            throw new RegraNegocioException("O desafio deve ter no mínimo 3 dias de duração.");
        }

        if (desafio.getGrupos() == null) {
            throw new RegraNegocioException("O desafio deve estar associado a um grupo.");
        }

        Grupo grupo = grupoRepository.getById(desafio.getGrupos().id());

        if (grupo.getStatus() != Status.ATIVO) {
            throw new RegraNegocioException("O grupo do desafio deve estar ativo.");
        }

        Categoria categoria = categoriaRepository.getById(desafio.getCategoria().id());

        if (categoria == null) {
            throw new RegraNegocioException("O desafio deve ter uma categoria.");
        }

        boolean nomeRepetido = existePorNome(desafio.getNome(),desafio);

        if (nomeRepetido) {
            throw new RegraNegocioException("Já existe um desafio com esse nome no grupo.");
        }

        Desafio desafioSalvo = desafiosRepository.save(desafio);


        MembrosDesafio membro = new MembrosDesafio();
        membro.setDesafio(desafioSalvo);
        Usuario usuario = usuarioRepository.buscarPorId(desafioSalvo.getCriador().getId());
        membro.setUsuario(usuario);
        membro.setStatus(Status.ATIVO);
        membro.setRole(TipoUsuario.ADMIN);
        membro.setDataEntrada(LocalDate.from(LocalDateTime.now()));

        membrosDesafioRepository.save(membro);

        return desafioSalvo;
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
