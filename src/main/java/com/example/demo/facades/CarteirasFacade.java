package com.example.demo.facades;

import com.example.demo.applications.CarteirasApplication;
import com.example.demo.entities.Carteira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarteirasFacade {

    private final CarteirasApplication carteirasApplication;

    @Autowired
    public CarteirasFacade(CarteirasApplication carteirasApplication) {
        this.carteirasApplication = carteirasApplication;
    }

    public Carteira salvar(Carteira carteira) {
        return carteirasApplication.salvar(carteira);
    }

    public Carteira buscarPorId(int id) {
        return carteirasApplication.buscarPorId(id);
    }

    public Carteira buscarPorUsuarioId(int idUsuario) {
        return carteirasApplication.buscarPorUsuarioId(idUsuario);
    }

    public List<Carteira> listarTodos() {
        return carteirasApplication.listarTodos();
    }

    public void deletar(int id) {
        carteirasApplication.deletar(id);
    }

    public boolean existePorId(int id) {
        return carteirasApplication.existePorId(id);
    }

    public boolean existePorUsuarioId(int idUsuario) {
        return carteirasApplication.existePorUsuarioId(idUsuario);
    }
}
