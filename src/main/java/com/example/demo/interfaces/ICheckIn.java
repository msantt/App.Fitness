package com.example.demo.interfaces;

import com.example.demo.entities.CheckIn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICheckIn {
    CheckIn salvar(CheckIn checkIn);

    CheckIn buscarPorId(int id);

    List<CheckIn> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<CheckIn> buscarPorIdUsuario(int idUsuario);

    List<CheckIn> buscarPorIntervaloDeDatas(LocalDateTime dataInicio, LocalDateTime dataFim);
}
