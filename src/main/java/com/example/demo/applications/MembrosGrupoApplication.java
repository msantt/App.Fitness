package com.example.demo.applications;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IMembrosGrupo;
import com.example.demo.records.UsuariosRecord;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.MembrosGrupoRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MembrosGrupoApplication implements IMembrosGrupo {


    private final GrupoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuariosApplication usuariosApplication;
    private MembrosGrupoRepository membrosGrupoRepository;
    private NotificacaoApplication notificacaoApplication;

    @Autowired
    public MembrosGrupoApplication(
        MembrosGrupoRepository membrosGrupoRepository,
        GrupoRepository grupoRepository,
        UsuarioRepository usuarioRepository,
        UsuariosApplication usuariosApplication,
        NotificacaoApplication notificacaoApplication
    ) {
        this.membrosGrupoRepository = membrosGrupoRepository;
        this.grupoRepository = grupoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuariosApplication = usuariosApplication;
        this.notificacaoApplication = notificacaoApplication;
    }

    @Override
    public MembrosGrupo salvar(MembrosGrupo membrosGrupo) {
        if (membrosGrupo.getStatus() == null) {
            membrosGrupo.setStatus(Status.ATIVO);
        }

        if (membrosGrupoRepository.existsByGrupo_UuidAndUsuario_Uuid(
                membrosGrupo.getGrupo().id(), membrosGrupo.getUsuario().getId())) {
            throw new IllegalArgumentException("Este usuário já é membro deste grupo.");
        }

        if (membrosGrupo.getGrupo() == null) {
            throw new IllegalArgumentException("O grupo não pode ser nulo.");
        }
        if (membrosGrupo.getUsuario() == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if (membrosGrupo.getDataEntrada() == null) {
            membrosGrupo.setDataEntrada(LocalDate.from(LocalDateTime.now()));
        }
        Grupo grupo = grupoRepository.findByUuid(membrosGrupo.getGrupo().id());
        if (grupo.getStatus() != Status.ATIVO) {
            throw new IllegalArgumentException("O grupo deve estar ativo.");
        }

        if (membrosGrupo.getRole() == null) {
            membrosGrupo.setRole(TipoUsuario.MEMBRO);
        }

        MembrosGrupo membroSalvo = membrosGrupoRepository.save(membrosGrupo);

        List<MembrosGrupo> membrosExistentes = membrosGrupoRepository.findByGrupo_Uuid(membrosGrupo.getGrupo().id());

        Usuario novoMembro = usuariosApplication.buscarPorUUID(membrosGrupo.getUsuario().getId());

        Grupo grupoNotificacao = grupoRepository.findByUuid(membrosGrupo.getGrupo().id());

        for (MembrosGrupo membro : membrosExistentes) {
            Usuario u = usuariosApplication.buscarPorUUID(membro.getUsuario().getId());
            if (!u.getId().equals(novoMembro.getId())) {
                String msg = novoMembro.getNome() + " entrou no grupo " + grupoNotificacao.getNome();
                notificacaoApplication.notificarUsuario(u, msg, TipoNotificacao.NOVO_MEMBRO);
            }
        }

        return membroSalvo;
    }

    @Override
    public MembrosGrupo buscarPorUUID(UUID id) {
        return membrosGrupoRepository.findByUuid(id);
    }

    @Override
    public List<MembrosGrupo> listarTodos() {
        return membrosGrupoRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
    membrosGrupoRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return membrosGrupoRepository.existsByUuid(id);
    }

    @Override
    public List<MembrosGrupo> buscarPorGrupo(UUID grupoId) {
        return membrosGrupoRepository.findByGrupo_Uuid( grupoId);
    }

    @Override
    public List<MembrosGrupo> buscarPorUsuario(UUID usuarioId) {
        return membrosGrupoRepository.findByUsuario_Uuid(usuarioId);
    }

    public List<MembrosGrupo> buscarPorStatus(Status status) {
        return membrosGrupoRepository.findByStatus(status);
    }

    @Override
    public MembrosGrupo buscarPorGrupoEUsuario(Grupo grupo, Usuario usuario) {
        return membrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);
    }

    @Override
    public List<MembrosGrupo> buscarPorRole(TipoUsuario role) {
        return membrosGrupoRepository.findByRole(role);
    }
}
