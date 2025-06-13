
package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.*;
import com.example.demo.repositories.ConviteRepository;
import com.example.demo.repositories.MembrosDesafioRepository;
import com.example.demo.repositories.MembrosGrupoRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConviteApplication {
    @Autowired
    private ConviteRepository conviteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MembrosDesafioRepository membroDesafioRepository;
    @Autowired
    private MembrosGrupoRepository membroGrupoRepository;
    @Autowired
    private MembrosGrupoApplication membrosGrupoApplication;
    @Autowired
    private DesafiosApplication desafiosApplication;
    @Autowired
    private GruposApplication gruposApplication;
    @Autowired
    private NotificacaoApplication notificacaoApplication;

    public ConviteApplication(ConviteRepository conviteRepository, UsuarioRepository usuarioRepository, MembrosDesafioRepository membroDesafioRepository, MembrosGrupoRepository membroGrupoRepository, MembrosGrupoApplication membrosGrupoApplication, DesafiosApplication desafiosApplication, GruposApplication gruposApplication, NotificacaoApplication notificacaoApplication) {
        this.conviteRepository = conviteRepository;
        this.usuarioRepository = usuarioRepository;
        this.membroDesafioRepository = membroDesafioRepository;
        this.membroGrupoRepository = membroGrupoRepository;
        this.membrosGrupoApplication = membrosGrupoApplication;
        this.desafiosApplication = desafiosApplication;
        this.gruposApplication = gruposApplication;
        this.notificacaoApplication = notificacaoApplication;
    }

    public Convite enviarConvite(UUID remetenteId, UUID convidadoId, UUID grupoOuDesafioId, boolean isGrupo) {

        if (isGrupo) {
            if (membroGrupoRepository.existsByGrupo_UuidAndUsuario_Uuid(grupoOuDesafioId, convidadoId)) {
                throw new RegraNegocioException("Usuário já é membro do grupo.");
            }
        } else {
            if (membroDesafioRepository.existsByUsuarioUuidAndDesafioUuid(convidadoId,grupoOuDesafioId)) {
                throw new RegraNegocioException("Usuário já é membro do desafio.");
            }
        }
        if (remetenteId == null || convidadoId == null || grupoOuDesafioId == null) {
            throw new RegraNegocioException("IDs não podem ser nulos.");
        }
        if (remetenteId.equals(convidadoId)) {
            throw new RegraNegocioException("Não é possível convidar a si mesmo.");
        }

        if (!isAdmin(remetenteId, grupoOuDesafioId, isGrupo)) {
            throw new RegraNegocioException("Apenas admin pode convidar.");
        }
        if (!conviteRepository.findByGrupoOuDesafioIdAndConvidado_Id(grupoOuDesafioId, convidadoId).isEmpty()) {
            throw new IllegalArgumentException("Usuário já convidado ou participante.");
        }
        Usuario remetente = usuarioRepository.findByUuid(remetenteId);
        Usuario convidado = usuarioRepository.findByUuid(convidadoId);
        if (remetente == null || convidado == null) {
            throw new RegraNegocioException("Usuário não encontrado.");
        }

        Convite convite = new Convite();
        convite.setRemetente(remetente);
        convite.setConvidado(convidado);
        convite.setGrupoOuDesafioId(grupoOuDesafioId);
        convite.setStatus(StatusConvite.PENDENTE);
        convite.setTipo(isGrupo ? TipoConvite.GRUPO : TipoConvite.DESAFIO);
        convite.setDataEnvio(LocalDateTime.now());

        Convite conviteSalvo = conviteRepository.save(convite);

        notificarConvite(conviteSalvo);

        return conviteSalvo;
    }

    public void responderConvite(UUID id, boolean aceitar) {
        Convite convite = conviteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Convite não encontrado."));
        if (aceitar) {
            convite.setStatus(StatusConvite.ACEITO);
            if(convite.getTipo() == TipoConvite.GRUPO) {
                adicionarMembroGrupo(convite);
            } else {
                adicionarMembroDesafio(convite);
            }
        } else {
            convite.setStatus(StatusConvite.RECUSADO);
        }
        conviteRepository.save(convite);
    }

    private boolean isAdmin(UUID usuarioId, UUID grupoOuDesafioId, boolean isGrupo) {
        return isGrupo
                ? membroGrupoRepository.existsByGrupoUuidAndUsuarioIdAndRole(grupoOuDesafioId, usuarioId, TipoUsuario.ADMIN)
                : membroDesafioRepository.existsByDesafioIdAndUsuarioIdAndRole(grupoOuDesafioId, usuarioId, TipoUsuario.ADMIN);
    }

    private void notificarConvite(Convite convite) {
        String mensagem = convite.getTipo() == TipoConvite.GRUPO
                ? "Você foi convidado para participar do grupo!"
                : "Você foi convidado para participar do desafio!";
        TipoNotificacao tipo = convite.getTipo() == TipoConvite.GRUPO
                ? TipoNotificacao.CONVITE_GRUPO
                : TipoNotificacao.CONVITE_DESAFIO;
        notificacaoApplication.notificarUsuario(convite.getConvidado(), mensagem, tipo);
    }

    private void adicionarMembroGrupo(Convite convite) {
        Grupo grupo = gruposApplication.buscarPorUUID(convite.getGrupoOuDesafioId());
        MembrosGrupo membrosGrupo = new MembrosGrupo();
        membrosGrupo.setUsuario(convite.getConvidado());
        membrosGrupo.setGrupo(grupo);
        membrosGrupo.setRole(TipoUsuario.MEMBRO);
        membrosGrupo.setDataEntrada(LocalDate.now());
        membrosGrupo.setStatus(Status.ATIVO);
        membrosGrupoApplication.salvar(membrosGrupo);
    }

    private void adicionarMembroDesafio(Convite convite) {
        Desafio desafio = desafiosApplication.buscarPorUUID(convite.getGrupoOuDesafioId());
        MembrosDesafio membrosDesafio = new MembrosDesafio();
        membrosDesafio.setUsuario(convite.getConvidado());
        membrosDesafio.setDesafio(desafio);
        membrosDesafio.setRole(TipoUsuario.MEMBRO);
        membrosDesafio.setDataEntrada(LocalDate.now());
        membrosDesafio.setStatus(Status.ATIVO);
        membroDesafioRepository.save(membrosDesafio);
    }
}