package com.example.demo.facades;

import com.example.demo.applications.PatrocinadorApplication;
import com.example.demo.entities.Patrocinador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PatrocinadorFacade {
    PatrocinadorApplication patrocinadorApplication;

    @Autowired
    public PatrocinadorFacade(PatrocinadorApplication patrocinadorApplication) {
        this.patrocinadorApplication = patrocinadorApplication;
    }

    public Patrocinador salvarPatrocinador(Patrocinador patrocinador) {
        return patrocinadorApplication.salvarPatrocinador(patrocinador);
    }

    public void deletarPatrocinador(UUID id) {
        patrocinadorApplication.deletarPatrocinador(id);
    }
    public Patrocinador atualizarPatrocinador(UUID id, Patrocinador patrocinador) {
        return patrocinadorApplication.salvarPatrocinador(patrocinador);
    }

    public List<Patrocinador> listarPatrocinadores() {
        return patrocinadorApplication.listarPatrocinadores();
    }

    public Patrocinador buscarPatrocinadorPorId(UUID id)
    {
        return patrocinadorApplication.buscarPatrocinadorPorUUID(id);
    }

    public Patrocinador buscarPatrocinadorPorNome(String nome) {
        return patrocinadorApplication.buscarPatrocinadorPorNome(nome);
    }
}
