package com.example.demo.Applications;

import com.example.demo.Entities.CheckIn;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Interfaces.IUsuarios;
import com.example.demo.Repositories.UsuariosRepository;
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
    public Usuarios salvar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public Optional<Usuarios> buscarPorId(int id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    @Override
    public List<Usuarios> listarTodos() {
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
    public List<Grupos> listarGruposPorUsuarioId(int idUsuario) {
        return usuariosRepository.findGruposPorUsuarioId(idUsuario);
    }

    @Override
    public List<CheckIn> listarCheckinsPorUsuarioId(int idUsuario) {
        return usuariosRepository.findCheckinsPorUsuarioId(idUsuario);
    }

    @Override
    public List<Usuarios> buscarAtivos() {
        return usuariosRepository.findByStatusTrue();
    }
}
