package com.example.demo.Facades;

import com.example.demo.Applications.CheckInApplication;
import com.example.demo.Entities.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CheckinFacede {
    CheckInApplication checkInApplication;

    @Autowired
    public CheckinFacede(CheckInApplication checkInApplication) {
        this.checkInApplication = checkInApplication;
    }


    public CheckIn salvar(CheckIn checkIn) {
        return checkInApplication.salvar(checkIn);
    }


    public Optional<CheckIn> buscarPorId(int id) {
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
