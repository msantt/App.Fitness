package com.example.demo.repositories;

import com.example.demo.entities.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecompensaRepository extends JpaRepository<Recompensa, Integer> {
    List<Recompensa> findByUsuarioId(int idUsuario);
    List<Recompensa> findByDesafioId(int idDesafio);
}
