package com.example.demo.applications;

import com.example.demo.entities.Patrocinador;
import com.example.demo.interfaces.IPatrocinador;
import com.example.demo.repositories.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrocinadorApplication implements IPatrocinador {

    PatrocinadorRepository patrocinadorRepository;
    @Autowired
    public PatrocinadorApplication(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }


    @Override
    public Patrocinador salvarPatrocinador(Patrocinador patrocinador) {
        return patrocinadorRepository.save(patrocinador);
    }

    @Override
    public void deletarPatrocinador(int id) {
        patrocinadorRepository.deleteById(id);
    }
    @Override
    public Patrocinador atualizarPatrocinador(int id, Patrocinador patrocinador) {
        return patrocinadorRepository.save(patrocinador);
    }

    @Override
    public List<Patrocinador> listarPatrocinadores() {
        return patrocinadorRepository.findAll();
    }

    @Override
    public Patrocinador buscarPatrocinadorPorId(int id) {

        return patrocinadorRepository.findById(id).orElseThrow();
    }

    @Override
    public Patrocinador buscarPatrocinadorPorNome(String nome) {
        return patrocinadorRepository.findByNome(nome);
    }
}
