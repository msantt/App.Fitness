package com.example.demo.repositories;

import com.example.demo.entities.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface RecompensaRepository extends JpaRepository<Recompensa, UUID> {
    List<Recompensa> findByMembroDesafioId(UUID idDesafio);
}
