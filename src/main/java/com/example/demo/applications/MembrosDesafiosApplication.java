package com.example.demo.applications;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IMembrosDesafio;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.DesafioRepository;
import com.example.demo.repositories.MembrosDesafioRepository;
import com.example.demo.repositories.MembrosGrupoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembrosDesafiosApplication implements IMembrosDesafio {

    private final MembrosDesafioRepository repository;
    private final DesafioRepository desafioRepository;
    private final CategoriaRepository categoriaRepository;
    private MembrosGrupoRepository membrosGrupoRepository;

    public MembrosDesafiosApplication(MembrosDesafioRepository repository, CategoriaRepository categoriaRepository, DesafioRepository desafioRepository,MembrosGrupoRepository membrosGrupoRepository) {
        this.repository = repository;
        this.desafioRepository = desafioRepository;
        this.categoriaRepository = categoriaRepository;
        this.membrosGrupoRepository = membrosGrupoRepository;

    }


    @Override
    public MembrosDesafio salvar(MembrosDesafio membroDesafio) {
        if (membroDesafio.getStatus() == null) {
            membroDesafio.setStatus(Status.ATIVO);
        }
        if (membroDesafio.getDesafio() == null || membroDesafio.getUsuario() == null) {
            throw new IllegalArgumentException("Desafio e Membro não podem ser nulos.");
        }

        if (membroDesafio.getUsuario() == null) {
            throw new IllegalArgumentException("Usuário deve ter um ID válido.");
        }

        if (membroDesafio.getDataEntrada() == null) {
            membroDesafio.setDataEntrada(LocalDate.now());
        }

        Desafio desafio = desafioRepository.findById(membroDesafio.getDesafio().id())
                .orElseThrow(() -> new IllegalArgumentException("Desafio não encontrado."));

        if (!desafio.getIsPublico()) {
            int grupoId = desafio.getGrupos().id();
            int usuarioId = membroDesafio.getUsuario().getId();
            boolean usuarioEhMembroDoGrupo = membrosGrupoRepository.existsByGrupo_IdAndUsuario_Id(grupoId, usuarioId);
            if (!usuarioEhMembroDoGrupo) {
                throw new IllegalStateException("Usuário não faz parte do grupo deste desafio privado.");
            }
        }

        if (desafio.getCategoria() == null ) {
            throw new IllegalStateException("O desafio informado não possui uma categoria válida.");
        }

        Categoria categoria = categoriaRepository.findById(desafio.getCategoria().id())
                .orElseThrow(() -> new IllegalStateException("Categoria não encontrada."));

        desafio.setCategoria(categoria);
        membroDesafio.setDesafio(desafio);

        if (!desafio.getStatus().equals(Status.ATIVO)) {
            throw new IllegalStateException("Não é possível participar de um desafio inativo ou encerrado.");
        }

        boolean jaCadastrado = repository.existsByUsuarioIdAndDesafioId(
                membroDesafio.getUsuario().getId(),
                desafio.getId()
        );

        if (jaCadastrado) {
            throw new IllegalStateException("O membro já está cadastrado neste desafio.");
        }

        return repository.save(membroDesafio);
    }


    @Override
    public List<MembrosDesafio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public MembrosDesafio buscarPorId(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void deletar(int id) {
        repository.deleteById(id);
    }
}
