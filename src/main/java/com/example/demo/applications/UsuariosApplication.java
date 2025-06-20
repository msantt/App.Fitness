package com.example.demo.applications;

import com.example.demo.entities.Desafio;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IUsuarios;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class UsuariosApplication implements IUsuarios {


    private UsuarioRepository usuariosRepository;

    @Autowired
    public UsuariosApplication(UsuarioRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (usuario.getStatus() == null) {
            usuario.setStatus(Status.ATIVO);
        }
        if (usuario.getDataCriacao() == null) {
            usuario.setDataCriacao(LocalDateTime.now());
        }

        usuario.setSaldo(java.math.BigDecimal.ZERO);

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
    public Usuario buscarPorUUID(UUID id) {
        return usuariosRepository.findByUuid(id);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return (Usuario) usuariosRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        usuariosRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorEmail(String email) {
        if (usuariosRepository.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return usuariosRepository.existsByUuid(id);
    }

    @Override
    public List<Grupo> listarGruposPorUsuarioUUID(UUID idUsuario) {
        return usuariosRepository.findGruposPorUsuarioUuid(idUsuario);
    }

    @Override
    public List<Usuario> buscarAtivos() {
        return usuariosRepository.findByStatusTrue();
    }

    public List<Desafio> recomendarDesafiosPopulares(UUID usuarioId) {
        Pageable top5 = PageRequest.of(0, 5);
        return usuariosRepository.recomendarDesafiosMaisPopularesPorObjetivo(usuarioId, top5).getContent();
    }

    public Usuario update(Usuario usuario) {
        Usuario existente = usuariosRepository.findByUuid(usuario.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!existente.getEmail().equals(usuario.getEmail()) && existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        existente.setDataNascimento(usuario.getDataNascimento());
        existente.setStatus(usuario.getStatus());
        existente.setSaldo(usuario.getSaldo());
        existente.setChavePix(usuario.getChavePix());
        existente.setObjetivo(usuario.getObjetivo());
        existente.setUrlFoto(usuario.getUrlFoto());

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            if (!usuario.getSenha().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                throw new IllegalArgumentException("Senha fraca: use pelo menos 8 caracteres, com letras maiúsculas, minúsculas e números.");
            }
            existente.setSenha(usuario.getSenha());
        }

        if (existente.getNome().length() < 3 || existente.getNome().length() > 100) {
            throw new IllegalArgumentException("O nome deve ter entre 3 e 100 caracteres.");
        }

        return usuariosRepository.save(existente);
    }

    public BigDecimal sacar(UUID usuarioId, BigDecimal valor) {
        Usuario usuario = usuariosRepository.findByUuid(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (usuario.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        usuario.setSaldo(usuario.getSaldo().subtract(valor));
        usuariosRepository.save(usuario);
        return usuario.getSaldo();
    }

    public BigDecimal depositar(UUID usuarioId, BigDecimal valor) {
        Usuario usuario = usuariosRepository.findByUuid(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        usuario.setSaldo(usuario.getSaldo().add(valor));
        usuariosRepository.save(usuario);
        return usuario.getSaldo();
    }
}
