package com.example.demo.interfaces;

import com.example.demo.entities.CheckIn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ICheckIn {
    CheckIn salvar(CheckIn checkIn);

    CheckIn buscarPorUUID(UUID id);

    List<CheckIn> listarTodos();

    void deletar(UUID id);

    boolean existePorUUID(UUID id);

    List<CheckIn> buscarPorMembrosDesafiosUUID(UUID membrosDesafiosUUID);

    List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim);
}
