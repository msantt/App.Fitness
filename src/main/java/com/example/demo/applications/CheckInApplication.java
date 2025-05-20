package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.ICheckIn;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CheckInApplication implements ICheckIn{


    private final DesafioRepository desafioRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private CheckInRepository checkInRepository;
    private MembrosDesafioRepository membroDesafioRepository;

    @Autowired
    public CheckInApplication(CheckInRepository checkInRepository, DesafioRepository desafioRepository, UsuarioRepository usuarioRepository, MembrosDesafioRepository membroDesafioRepository, CategoriaRepository categoriaRepository) {
        this.checkInRepository = checkInRepository;
        this.desafioRepository = desafioRepository;
        this.usuarioRepository = usuarioRepository;
        this.membroDesafioRepository = membroDesafioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public CheckIn salvar(CheckIn checkIn) {
        if (checkIn.getStatus() == null) {
            checkIn.setStatus(Status.PENDENTE);
        }

        if (checkIn.getDataHoraCheckin() == null) {
            checkIn.setDataHoraCheckin(LocalDateTime.now());
        }

        if (checkIn.getDataHoraCheckin().isBefore(LocalDateTime.now())) {
            throw new RegraNegocioException("Check-in não pode ser em data passada.");
        }
        MembrosDesafio membro = membroDesafioRepository.findById(checkIn.getMembroDesafio().id())
                .orElseThrow(() -> new RegraNegocioException("Membro do desafio não encontrado"));

        if (!membro.getStatus().equals(Status.ATIVO)) {
            throw new RegraNegocioException("Membro do desafio não está ativo.");
        }

        Desafio desafio = desafioRepository.findById(membro.getDesafio().getId())
                .orElseThrow(() -> new RegraNegocioException("Desafio não encontrado"));

        if (!desafio.getStatus().equals(Status.ATIVO)) {
            throw new RegraNegocioException("Check-in só permitido para desafios ativos.");
        }

        Categoria categoria = categoriaRepository.getById(desafio.getCategoria().id());

        if (categoria == null) {
            throw new RegraNegocioException("Desafio não tem categoria associada.");
        }

        Usuario usuario = usuarioRepository.findById(membro.getUsuario().getId())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        boolean jaFezCheckinHoje = checkInRepository
                .existsByMembroDesafio_Usuario_IdAndMembroDesafio_Desafio_IdAndDataHoraCheckinBetween(
                        usuario.getId(), desafio.getId(), inicioDoDia, fimDoDia
                );

        if (jaFezCheckinHoje) {
            throw new RegraNegocioException("Usuário já fez check-in hoje para esse desafio.");
        }
        checkIn.setMembroDesafio(membro);

        checkIn.setStatus(Status.CONCLUIDO);
        return checkInRepository.save(checkIn);
    }


    @Override
    public CheckIn buscarPorId(int id) {
        return checkInRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CheckIn> listarTodos() {
        return checkInRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        checkInRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return(checkInRepository.existsById(id));
    }

    @Override
    public List<CheckIn> buscarPorMembrosDesafiosId(int membrosDesafiosId) {
        return checkInRepository.findByMembroDesafioId(membrosDesafiosId);
    }

    @Override
    public List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return checkInRepository.findByDataHoraCheckinBetween(dataInicio, dataFim);
    }
}
