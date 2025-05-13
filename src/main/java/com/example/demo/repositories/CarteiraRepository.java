package com.example.demo.repositories;

import com.example.demo.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

    Carteira findByIdUsuario(int idUsuario);

    boolean existsByIdUsuario(int idUsuario);
}
