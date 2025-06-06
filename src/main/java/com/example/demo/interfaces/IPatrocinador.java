package com.example.demo.interfaces;

import com.example.demo.entities.Patrocinador;

import java.util.List;
import java.util.UUID;

public interface IPatrocinador {
    Patrocinador salvarPatrocinador(Patrocinador patrocinador);

    void deletarPatrocinador(UUID id);

    Patrocinador atualizarPatrocinador(UUID id, Patrocinador patrocinador);

    List<Patrocinador> listarPatrocinadores();

    Patrocinador buscarPatrocinadorPorUUID(UUID id);

    Patrocinador buscarPatrocinadorPorNome(String nome);
}
