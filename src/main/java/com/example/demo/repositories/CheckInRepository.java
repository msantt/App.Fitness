package com.example.demo.repositories;

import com.example.demo.entities.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

    List<CheckIn> findByUsuarioId(int usuarioId);

    List<CheckIn> findByDesafioId(int desafioId);

    List<CheckIn> findByDataHoraCheckinBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
