package com.example.demo.facades;

import com.example.demo.applications.CheckInApplication;
import com.example.demo.entities.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    public CheckIn buscarPorId(int id) {
        return checkInApplication.buscarPorId(id);
    }


    public List<CheckIn> listarTodos() {
        return checkInApplication.listarTodos();
    }


    public void deletar(int id) {
        checkInApplication.deletar(id);
    }


    public boolean existePorId(int id) {
        return(checkInApplication.existePorId(id));
    }


    public List<CheckIn> buscarPorIdUsuario(int idUsuario) {
        return checkInApplication.buscarPorIdUsuario(idUsuario);
    }


    public List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return checkInApplication.buscarPorIntervaloDeDatas(dataInicio, dataFim);
    }
}
