package com.example.demo.applications;

import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IUsuarios;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Service
public class UsuariosApplication implements IUsuarios {


    private UsuarioRepository usuariosRepository;

    @Autowired
    public UsuariosApplication(UsuarioRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        if (usuario.getStatus() == null) {
            usuario.setStatus(Status.ATIVO);
        }
        if (usuario.getDataCriacao() == null) {
            usuario.setDataCriacao(LocalDateTime.now());
        }

        if (usuario.getNome().length() < 3 || usuario.getNome().length() > 100) {
            throw new IllegalArgumentException("O nome deve ter entre 3 e 100 caracteres.");
        }

        if (existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (!usuario.getSenha().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException("Senha fraca: use pelo menos 8 caracteres, com letras maiúsculas, minúsculas e números.");
        }

        int idade = Period.between(usuario.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O usuário deve ter pelo menos 18 anos.");
        }

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
    public List<Usuario> buscarAtivos() {
        return usuariosRepository.findByStatusTrue();
    }

    public List<Desafio> recomendarDesafiosPopulares(int usuarioId) {
        Pageable top5 = PageRequest.of(0, 5);
        return usuariosRepository.recomendarDesafiosMaisPopularesPorObjetivo(usuarioId, top5).getContent();
    }

}
