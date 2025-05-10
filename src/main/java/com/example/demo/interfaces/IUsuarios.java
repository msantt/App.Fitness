package com.example.demo.interfaces;

import com.example.demo.entities.CheckIn;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarios {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(int id);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarTodos();

    void deletar(int id);

    boolean existePorEmail(String email);

    boolean existePorId(int id);

    List<Grupo> listarGruposPorUsuarioId(int idUsuario);

    List<CheckIn> listarCheckinsPorUsuarioId(int idUsuario);

    List<Usuario> buscarAtivos();

}
