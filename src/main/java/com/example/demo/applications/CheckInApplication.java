package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.interfaces.ICheckIn;
import com.example.demo.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class CheckInApplication implements ICheckIn{


    private final DesafioRepository desafioRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final NotificacaoApplication notificacaoApplication;
    private CheckInRepository checkInRepository;
    private MembrosDesafioRepository membroDesafioRepository;
    private PontuacaoRepository pontuacaoRepository;

    @Autowired
    public CheckInApplication(CheckInRepository checkInRepository, DesafioRepository desafioRepository, UsuarioRepository usuarioRepository, MembrosDesafioRepository membroDesafioRepository, CategoriaRepository categoriaRepository, NotificacaoApplication notificacaoApplication,PontuacaoRepository pontuacaoRepository) {
        this.checkInRepository = checkInRepository;
        this.desafioRepository = desafioRepository;
        this.usuarioRepository = usuarioRepository;
        this.membroDesafioRepository = membroDesafioRepository;
        this.categoriaRepository = categoriaRepository;
        this.notificacaoApplication = notificacaoApplication;
        this.pontuacaoRepository = pontuacaoRepository;
    }

    @Transactional
    public CheckIn salvar(CheckIn checkIn) {
        if (checkIn.getStatus() == null) {
            checkIn.setStatus(Status.PENDENTE);
        }

        if (checkIn.getDataHoraCheckin() == null) {
            checkIn.setDataHoraCheckin(LocalDateTime.now());
        }

//        if (checkIn.getDataHoraCheckin().isBefore(LocalDateTime.now())) {
//            throw new RegraNegocioException("Check-in não pode ser em data passada.");
//        }
        MembrosDesafio membro = membroDesafioRepository.findByUuid(checkIn.getMembroDesafio().id());
        if (membro == null) {
            throw new RegraNegocioException("Membro do desafio não encontrado");
        }
        if (!membro.getStatus().equals(Status.ATIVO)) {
            throw new RegraNegocioException("Membro do desafio não está ativo.");
        }

        Desafio desafio = desafioRepository.findByUuid(membro.getDesafio().getId());
                if (desafio == null) {
                    throw new RegraNegocioException("Desafio não encontrado");
                }

        if (!desafio.getStatus().equals(Status.ATIVO)) {
            throw new RegraNegocioException("Check-in só permitido para desafios ativos.");
        }

        Categoria categoria = categoriaRepository.findByUuid(desafio.getCategoria().id());

        if (categoria == null) {
            throw new RegraNegocioException("Desafio não tem categoria associada.");
        }

        Usuario usuario = usuarioRepository.findByUuid(membro.getUsuario().getId());
        if (usuario == null) {
            throw new RegraNegocioException("Usuário não encontrado");
        }

        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        boolean jaFezCheckinHoje = checkInRepository
                .existsByMembroDesafio_Usuario_UuidAndMembroDesafio_Desafio_UuidAndDataHoraCheckinBetween(
                        usuario.getId(), desafio.getId(), inicioDoDia, fimDoDia
                );

        if (jaFezCheckinHoje) {
            throw new RegraNegocioException("Usuário já fez check-in hoje para esse desafio.");
        }
        checkIn.setMembroDesafio(membro);
        checkIn.setStatus(Status.CONCLUIDO);

        CheckIn checkInSalvo = checkInRepository.save(checkIn);

        List<MembrosDesafio> membros = membroDesafioRepository.findByDesafioUuid(desafio.getId());
        Usuario quemFez = usuario;
        for (MembrosDesafio m : membros) {
            Usuario u = m.getUsuario();
            if (!u.getId().equals(quemFez.getId())) {
                String msg = quemFez.getNome() + " fez um check-in no desafio " + desafio.getNome();
                notificacaoApplication.notificarUsuario(u, msg, TipoNotificacao.CHECK_IN);
            }
        }


        List<MembrosDesafio> desafiosDoUsuario = membroDesafioRepository.findByUsuarioUuid(usuario.getId());
        for (MembrosDesafio desafioUsuario : desafiosDoUsuario) {
            Pontuacao pontuacao = pontuacaoRepository.findByMembroDesafioUuid(desafioUsuario.getId());
            if (pontuacao != null) {
                pontuacao.registrarCheckin(LocalDate.now());
                pontuacaoRepository.save(pontuacao);
            }
        }

        return checkInSalvo;
    }


    @Override
    public CheckIn buscarPorUUID(UUID id) {
        return checkInRepository.findByUuid(id);
    }

    @Override
    public List<CheckIn> listarTodos() {
        return checkInRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        checkInRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return(checkInRepository.existsByUuid(id));
    }

    @Override
    public List<CheckIn> buscarPorMembrosDesafiosUUID(UUID membrosDesafiosId) {
        return checkInRepository.findByMembroDesafioUuid(membrosDesafiosId);
    }

    @Override
    public List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return checkInRepository.findByDataHoraCheckinBetween(dataInicio, dataFim);
    }

    public List<CheckIn> buscarPorDesafioUUID(UUID desafioId) {
        return checkInRepository.findByMembroDesafio_Desafio_Uuid(desafioId);
    }

    public List<CheckIn> buscarPorUsuarioUUID(UUID usuarioId) {
        return checkInRepository.findByMembroDesafio_Usuario_Uuid(usuarioId);
    }
}
