package com.example.demo.facades;

import com.example.demo.applications.CheckInApplication;
import com.example.demo.entities.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CheckinFacade {
    CheckInApplication checkInApplication;

    @Autowired
    public CheckinFacade(CheckInApplication checkInApplication) {
        this.checkInApplication = checkInApplication;
    }


    public CheckIn salvar(CheckIn checkIn) {
        return checkInApplication.salvar(checkIn);
    }


    public CheckIn buscarPorId(UUID id) {
        return checkInApplication.buscarPorUUID(id);
    }


    public List<CheckIn> listarTodos() {
        return checkInApplication.listarTodos();
    }


    public void deletar(UUID id) {
        checkInApplication.deletar(id);
    }


    public boolean existePorId(UUID id) {
        return(checkInApplication.existePorUUID(id));
    }


    public List<CheckIn> buscarPorMembrosDesafiosId(UUID membrosDesafiosId) {
        return checkInApplication.buscarPorMembrosDesafiosUUID(membrosDesafiosId);
    }


    public List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return checkInApplication.buscarPorIntervaloDeDatas(dataInicio, dataFim);
    }

    public List<CheckIn> buscarPorDesafioId(UUID desafioId) {
        return checkInApplication.buscarPorDesafioUUID(desafioId);
    }

    public List<CheckIn> buscarPorUsuarioId(UUID usuarioId) {
        return checkInApplication.buscarPorUsuarioUUID(usuarioId);
    }
}
