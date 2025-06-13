package com.example.demo.applications;

import com.example.demo.entities.Patrocinador;
import com.example.demo.interfaces.IPatrocinador;
import com.example.demo.repositories.PatrocinadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatrocinadorApplication implements IPatrocinador {

    PatrocinadorRepository patrocinadorRepository;
    @Autowired
    public PatrocinadorApplication(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }


    @Override
    @Transactional
    public Patrocinador salvarPatrocinador(Patrocinador patrocinador) {
        if (patrocinador.getNome() == null || patrocinador.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do patrocinador não pode ser nulo ou vazio.");
        }
        if (patrocinador.getNome().length() < 3 || patrocinador.getNome().length() > 20) {
            throw new IllegalArgumentException("O nome do patrocinador deve ter entre 3 e 20 caracteres.");
        }
        Optional<Patrocinador> existente = Optional.ofNullable(patrocinadorRepository.findByNome(patrocinador.getNome()));
        if (existente.isPresent() && (patrocinador.getId() == null || !existente.get().getId().equals(patrocinador.getId()))) {
            throw new IllegalArgumentException("Já existe um patrocinador com esse nome.");
        }

        if (patrocinador.getCnpj().isEmpty() && !isCnpjValido(patrocinador.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        return patrocinadorRepository.save(patrocinador);
    }

    @Override
    public void deletarPatrocinador(UUID id) {
        patrocinadorRepository.deleteByUuid(id);
    }
    @Override
    public Patrocinador atualizarPatrocinador(UUID id, Patrocinador patrocinador) {
        return patrocinadorRepository.save(patrocinador);
    }

    @Override
    public List<Patrocinador> listarPatrocinadores() {
        return patrocinadorRepository.findAll();
    }

    @Override
    public Patrocinador buscarPatrocinadorPorUUID(UUID id) {

        return patrocinadorRepository.findByUuid(id);
    }

    @Override
    public Patrocinador buscarPatrocinadorPorNome(String nome) {
        return patrocinadorRepository.findByNome(nome);
    }

    private boolean isCnpjValido(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos1[i];
            }
            int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos2[i];
            }
            int digito2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

            return digito1 == (cnpj.charAt(12) - '0') && digito2 == (cnpj.charAt(13) - '0');
        } catch (Exception e) {
            return false;
        }
    }
}
