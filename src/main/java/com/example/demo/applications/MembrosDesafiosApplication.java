package com.example.demo.applications;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.MembrosDesafio;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IMembrosDesafio;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.DesafioRepository;
import com.example.demo.repositories.MembrosDesafioRepository;
import com.example.demo.repositories.MembrosGrupoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MembrosDesafiosApplication implements IMembrosDesafio {

    private final MembrosDesafioRepository repository;
    private final DesafioRepository desafioRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuariosApplication usuariosApplication;
    private final MembrosDesafioRepository membrosDesafioRepository;
    private MembrosGrupoRepository membrosGrupoRepository;
    private NotificacaoApplication notificacaoApplication;

    public MembrosDesafiosApplication(MembrosDesafioRepository repository, CategoriaRepository categoriaRepository, DesafioRepository desafioRepository, MembrosGrupoRepository membrosGrupoRepository, UsuariosApplication usuariosApplication, NotificacaoApplication notificacaoApplication, MembrosDesafioRepository membrosDesafioRepository) {
        this.repository = repository;
        this.desafioRepository = desafioRepository;
        this.categoriaRepository = categoriaRepository;
        this.membrosGrupoRepository = membrosGrupoRepository;
        this.usuariosApplication = usuariosApplication;
        this.notificacaoApplication = notificacaoApplication;
        this.membrosDesafioRepository = membrosDesafioRepository;
    }


    @Override
    public MembrosDesafio salvar(MembrosDesafio membroDesafio) {
        if(membroDesafio.getRole() == null){
            membroDesafio.setRole(TipoUsuario.MEMBRO);
        }
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

        Desafio desafio = desafioRepository.findByUuid(membroDesafio.getDesafio().getId());
        if (desafio == null) {
            throw new IllegalArgumentException("Desafio não encontrado.");
        }

        if (!desafio.getIsPublico()) {
            UUID grupoId = desafio.getGrupos().id();
            UUID usuarioId = membroDesafio.getUsuario().getId();
            boolean usuarioEhMembroDoGrupo = membrosGrupoRepository.existsByGrupo_UuidAndUsuario_Uuid(grupoId, usuarioId);
            if (!usuarioEhMembroDoGrupo) {
                throw new IllegalStateException("Usuário não faz parte do grupo deste desafio privado.");
            }
        }

        if (desafio.getCategoria() == null ) {
            throw new IllegalStateException("O desafio informado não possui uma categoria válida.");
        }

        Categoria categoria = categoriaRepository.findByUuid(desafio.getCategoria().id());
        if (categoria == null) {
            throw new IllegalStateException("Categoria não encontrada.");
        }

        desafio.setCategoria(categoria);
        membroDesafio.setDesafio(desafio);

        if (!desafio.getStatus().equals(Status.ATIVO)) {
            throw new IllegalStateException("Não é possível participar de um desafio inativo ou encerrado.");
        }

        boolean jaCadastrado = repository.existsByUsuarioUuidAndDesafioUuid(
                membroDesafio.getUsuario().getId(),
                desafio.getId()
        );

        if (jaCadastrado) {
            throw new IllegalStateException("O membro já está cadastrado neste desafio.");
        }

        MembrosDesafio membroSalvo = membrosDesafioRepository.save(membroDesafio);

        List<MembrosDesafio> membrosExistentes = membrosDesafioRepository.findByDesafioUuid(membroDesafio.getDesafio().getId());

        Usuario novoMembro = usuariosApplication.buscarPorUUID(membroDesafio.getUsuario().getId());

        Desafio desafioNotificacao = desafioRepository.findByUuid(membroDesafio.getDesafio().getId());

        for (MembrosDesafio membro : membrosExistentes) {
            Usuario u = usuariosApplication.buscarPorUUID(membro.getUsuario().getId());
            if (!u.getId().equals(novoMembro.getId())) {
                String msg = novoMembro.getNome() + " entrou no desafio " + desafioNotificacao.getNome();
                notificacaoApplication.notificarUsuario(u, msg, TipoNotificacao.NOVO_MEMBRO_DESAFIO);
            }
        }

        return membroSalvo;
    }


    @Override
    public List<MembrosDesafio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public MembrosDesafio buscarPorUUID(UUID id) {
        return repository.findByUuid(id);
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteByUuid(id);
    }

    @Override
    public List<MembrosDesafio> buscarPorDesafioUUID(UUID desafioId) {
        return repository.findByDesafioUuid(desafioId);
    }
}
