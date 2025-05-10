package com.example.demo.interfaces;

import com.example.demo.entities.Patrocinador;

import java.util.List;
import java.util.Optional;

public interface IPatrocinador {
    Patrocinador salvarPatrocinador(Patrocinador patrocinador);
    void deletarPatrocinador(int id);
    Patrocinador atualizarPatrocinador(int id,Patrocinador patrocinador);
    List<Patrocinador> listarPatrocinadores();
    Patrocinador buscarPatrocinadorPorId(int id);
    Patrocinador buscarPatrocinadorPorNome(String nome);
}
