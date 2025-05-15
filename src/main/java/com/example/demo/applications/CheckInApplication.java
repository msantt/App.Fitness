package com.example.demo.applications;

import com.example.demo.entities.CheckIn;
import com.example.demo.interfaces.ICheckIn;
import com.example.demo.repositories.CheckInRepository;
import com.example.demo.repositories.DesafioRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CheckInApplication implements ICheckIn{


    private final DesafioRepository desafioRepository;
    private final UsuarioRepository usuarioRepository;
    private CheckInRepository checkInRepository;

    @Autowired
    public CheckInApplication(CheckInRepository checkInRepository, DesafioRepository desafioRepository, UsuarioRepository usuarioRepository) {
        this.checkInRepository = checkInRepository;
        this.desafioRepository = desafioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CheckIn salvar(CheckIn checkIn) {

        if (checkIn.getDataHoraCheckin() == null) {
            throw new IllegalArgumentException("A data e hora do check-in são obrigatórias.");
        }

        if (checkIn.getDataHoraCheckin().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Check-in deve ser uma data futura.");
        }

        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDoDia = hoje.atStartOfDay(); // 00:00
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX); // 23:59:59.999...

//        // Buscar desafio e verificar se a categoria está presente
//        Desafio desafio = desafioRepository.getById(checkIn.getDesafio().getId());
//        if (desafio.getCategoria() == null) {
//            throw new IllegalArgumentException("Desafio não tem categoria associada.");
//        }
//
//        Usuario usuario = usuarioRepository.getById(checkIn.getUsuario().getId());
//
//        boolean jaFezCheckinHoje = checkInRepository.existsByUsuarioIdAndDesafioIdAndDataHoraCheckinBetween(
//                usuario.getId(),
//                desafio.getId(),
//                inicioDoDia,
//                fimDoDia
//        );
//
//        if (jaFezCheckinHoje) {
//            throw new IllegalStateException("Usuário já fez check-in hoje para esse desafio.");
//        }
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
