package com.example.demo.Repositories;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.Desafios;
import com.example.demo.Entities.Grupos;
import com.example.demo.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesafiosRepository extends JpaRepository<Desafios, Integer> {

    List<Desafios> findByNome(String nome);

    List<Desafios> findByStatus(Status status);

    List<Desafios> findByGrupos_Id(int idGrupo);
    List<Desafios> findByCategoria_Id(int idCategoria);

    List<Desafios> findByIsPublico(Boolean isPublico);
}
