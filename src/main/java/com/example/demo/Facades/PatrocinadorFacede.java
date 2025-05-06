package com.example.demo.Facades;

import com.example.demo.Applications.PatrocinadorApplication;
import com.example.demo.Entities.Patrocinador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatrocinadorFacede {
    PatrocinadorApplication patrocinadorApplication;

    @Autowired
    public PatrocinadorFacede(PatrocinadorApplication patrocinadorApplication) {
        this.patrocinadorApplication = patrocinadorApplication;
    }

    public Patrocinador salvarPatrocinador(Patrocinador patrocinador) {
        return patrocinadorApplication.salvarPatrocinador(patrocinador);
    }

    public void deletarPatrocinador(int id) {
        patrocinadorApplication.deletarPatrocinador(id);
    }
    public Patrocinador atualizarPatrocinador(int id, Patrocinador patrocinador) {
        return patrocinadorApplication.salvarPatrocinador(patrocinador);
    }

    public List<Patrocinador> listarPatrocinadores() {
        return patrocinadorApplication.listarPatrocinadores();
    }

    public Optional<Patrocinador> buscarPatrocinadorPorId(int id)
    {

        return patrocinadorApplication.buscarPatrocinadorPorId(id);
    }

    public Optional<Patrocinador> buscarPatrocinadorPorNome(String nome) {
        return patrocinadorApplication.buscarPatrocinadorPorNome(nome);
    }
}
