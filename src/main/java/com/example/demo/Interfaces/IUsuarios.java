package com.example.demo.Interfaces;

import com.example.demo.Entities.CheckIn;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface IUsuarios {

    Usuarios salvar(Usuarios usuario);

    Optional<Usuarios> buscarPorId(int id);

    Optional<Usuarios> buscarPorEmail(String email);

    List<Usuarios> listarTodos();

    void deletar(int id);

    boolean existePorEmail(String email);

    boolean existePorId(int id);

    List<Grupos> buscarGruposPorUsuarioId(int idUsuario);

    List<CheckIn> buscarCheckinsPorUsuarioId(int idUsuario);

    List<Usuarios> buscarAtivos();

}
