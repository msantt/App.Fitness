package com.example.demo.Interfaces;

import com.example.demo.Entities.CheckIn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICheckIn {
    CheckIn salvar(CheckIn checkIn);

    Optional<CheckIn> buscarPorId(int id);

    List<CheckIn> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<CheckIn> buscarPorIdUsuario(int idUsuario);

    List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim);
}
