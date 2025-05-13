package com.example.demo.applications;

import com.example.demo.entities.Carteira;
import com.example.demo.interfaces.ICarteiras;
import com.example.demo.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteirasApplication implements ICarteiras {

    private final CarteiraRepository carteiraRepository;

    @Autowired
    public CarteirasApplication(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public Carteira salvar(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Override
    public Carteira buscarPorId(int id) {
        return carteiraRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Carteira> listarTodos() {
        return carteiraRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        carteiraRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return carteiraRepository.findById(id).isPresent();
    }

    @Override
    public Carteira buscarPorUsuarioId(int idUsuario) {
        return carteiraRepository.findByIdUsuario(idUsuario);
    }
}
