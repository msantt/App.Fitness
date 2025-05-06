package com.example.demo.Interfaces;

import com.example.demo.Entities.Patrocinador;

import java.util.List;
import java.util.Optional;

public interface IPatrocinador {
    Patrocinador salvarPatrocinador(Patrocinador patrocinador);
    void deletarPatrocinador(int id);
    Patrocinador atualizarPatrocinador(int id,Patrocinador patrocinador);
    List<Patrocinador> listarPatrocinadores();
    Optional<Patrocinador> buscarPatrocinadorPorId(int id);
    Optional<Patrocinador> buscarPatrocinadorPorNome(String nome);
}
