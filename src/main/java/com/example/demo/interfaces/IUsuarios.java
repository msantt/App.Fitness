package com.example.demo.interfaces;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarios {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorUUID(UUID id);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarTodos();

    void deletar(UUID id);

    boolean existePorEmail(String email);

    boolean existePorUUID(UUID id);

    List<Grupo> listarGruposPorUsuarioUUID(UUID idUsuario);

    List<Usuario> buscarAtivos();

}
