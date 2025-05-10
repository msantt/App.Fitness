package com.example.demo.applications;

import com.example.demo.entities.CheckIn;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import com.example.demo.interfaces.IUsuarios;
import com.example.demo.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosApplication implements IUsuarios {


    private UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosApplication(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(int id) {
        return usuariosRepository.findById(id).orElseThrow();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        usuariosRepository.deleteById(id);
    }

    @Override
    public boolean existePorEmail(String email) {
        if (usuariosRepository.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existePorId(int id) {
        return usuariosRepository.existsById(id);
    }

    @Override
    public List<Grupo> listarGruposPorUsuarioId(int idUsuario) {
        return usuariosRepository.findGruposPorUsuarioId(idUsuario);
    }

    @Override
    public List<CheckIn> listarCheckinsPorUsuarioId(int idUsuario) {
        return usuariosRepository.findCheckinsPorUsuarioId(idUsuario);
    }

    @Override
    public List<Usuario> buscarAtivos() {
        return usuariosRepository.findByStatusTrue();
    }
}
