package com.example.demo.Repositories;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

    List<CheckIn> findByUsuarioId(int usuarioId);

    List<CheckIn> findByDesafioId(int desafioId);

    List<CheckIn> findByDataHoraCheckinBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
