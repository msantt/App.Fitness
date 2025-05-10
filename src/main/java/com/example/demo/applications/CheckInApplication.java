package com.example.demo.applications;

import com.example.demo.entities.CheckIn;
import com.example.demo.interfaces.ICheckIn;
import com.example.demo.repositories.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CheckInApplication implements ICheckIn{


    private CheckInRepository checkInRepository;

    @Autowired
    public CheckInApplication(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @Override
    public CheckIn salvar(CheckIn checkIn) {
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
    public List<CheckIn> buscarPorIdUsuario(int idUsuario) {
        return checkInRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return checkInRepository.findByDataHoraCheckinBetween(dataInicio, dataFim);
    }
}
