package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IDesafios;
import com.example.demo.records.UsuariosRecord;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DesafiosApplication implements IDesafios {


    private final GrupoRepository grupoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MembrosDesafioRepository membrosDesafioRepository;
    private final MembrosGrupoRepository membrosGrupoRepository;
    private NotificacaoApplication notificacaoApplication;
    private DesafioRepository desafiosRepository;
    private UsuariosApplication usuarioRepository;

    @Autowired
    public DesafiosApplication(DesafioRepository desafiosRepository, GrupoRepository grupoRepository, CategoriaRepository categoriaRepository, UsuariosApplication usuarioRepository, MembrosDesafioRepository membrosDesafioRepository, MembrosGrupoRepository membrosGrupoRepository,NotificacaoApplication notificacaoApplication) {
        this.desafiosRepository = desafiosRepository;
        this.grupoRepository = grupoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.membrosDesafioRepository = membrosDesafioRepository;
        this.membrosGrupoRepository = membrosGrupoRepository;
        this.notificacaoApplication = notificacaoApplication;
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

        Grupo grupo = grupoRepository.findByUuid(desafio.getGrupos().id());

        if (grupo.getStatus() != Status.ATIVO) {
            throw new RegraNegocioException("O grupo do desafio deve estar ativo.");
        }

        Categoria categoria = categoriaRepository.findByUuid(desafio.getCategoria().id());

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
        Usuario usuario = usuarioRepository.buscarPorUUID(desafioSalvo.getCriador().getId());
        membro.setUsuario(usuario);
        membro.setStatus(Status.ATIVO);
        membro.setRole(TipoUsuario.ADMIN);
        membro.setDataEntrada(LocalDate.from(LocalDateTime.now()));

        membrosDesafioRepository.save(membro);
        List<MembrosGrupo> membrosGrupo = membrosGrupoRepository.findByGrupo_Uuid(grupo.getId());
        Usuario criador = usuario;
        for (MembrosGrupo membroGrupo : membrosGrupo) {
            // Buscar a entidade Usuario pelo UUID do UsuariosRecord
            Usuario u = usuarioRepository.buscarPorUUID(membroGrupo.getUsuario().getId());
            if (!u.getId().equals(criador.getId())) {
                String msg = criador.getNome() + " criou um novo desafio no grupo " + grupo.getNome() + ": " + desafio.getNome();
                notificacaoApplication.notificarUsuario(u, msg, TipoNotificacao.NOVO_DESAFIO);
            }
        }
        return desafioSalvo;
    }

    @Override
    public Desafio buscarPorUUID(UUID id) {
        return desafiosRepository.findByUuid(id);
    }

    @Override
    public List<Desafio> listarTodos() {
        return desafiosRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        desafiosRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return desafiosRepository.existsByUuid(id);
    }

    @Override
    public List<Desafio> buscarPorUUIDGrupo(UUID idGrupo) {
        return desafiosRepository.findByGrupo_Uuid(idGrupo);
    }

    @Override
    public List<Desafio> buscarPorUUIDCategoria(UUID idCategoria) {
        return desafiosRepository.findByCategoria_Uuid(idCategoria);
    }

    @Override
    public List<Desafio> buscarPorStatus(Status status) {
        return desafiosRepository.findByStatus(status);
    }

    public boolean existePorNome(String nome, Desafio desafio) {
        return desafiosRepository
                .findByGrupo_Uuid(desafio.getGrupos().id())
                .stream()
                .anyMatch(d -> d.getNome().equalsIgnoreCase(desafio.getNome()));
    }
}
