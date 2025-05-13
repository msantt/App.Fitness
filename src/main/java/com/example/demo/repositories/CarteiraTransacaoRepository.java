package com.example.demo.repositories;

import com.example.demo.entities.CarteiraTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteiraTransacaoRepository extends JpaRepository<CarteiraTransacao, Integer> {

    List<CarteiraTransacao> findByIdCarteira(int idCarteira);
}
