package com.example.demo.applications;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IMembrosGrupo;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.MembrosGrupoRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MembrosGrupoApplication implements IMembrosGrupo {


    private final GrupoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;
    private MembrosGrupoRepository membrosGrupoRepository;

    @Autowired
    public MembrosGrupoApplication(MembrosGrupoRepository membrosGrupoRepository, GrupoRepository grupoRepository, UsuarioRepository usuarioRepository) {
        this.membrosGrupoRepository = membrosGrupoRepository;
        this.grupoRepository = grupoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public MembrosGrupo salvar(MembrosGrupo membrosGrupo) {
        if (membrosGrupo.getStatus() == null) {
            membrosGrupo.setStatus(Status.ATIVO);
        }

        if (membrosGrupoRepository.existsByGrupo_IdAndUsuario_Id(
                membrosGrupo.getGrupo().id(), membrosGrupo.getUsuario().getId())) {
            throw new IllegalArgumentException("Este usuário já é membro deste grupo.");
        }

        if (membrosGrupo.getGrupo() == null) {
            throw new IllegalArgumentException("O grupo não pode ser nulo.");
        }
        if (membrosGrupo.getUsuario() == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if (membrosGrupo.getDataEntrada() == null) {
            membrosGrupo.setDataEntrada(LocalDate.from(LocalDateTime.now()));
        }
        Grupo grupo = grupoRepository.getById(membrosGrupo.getGrupo().id());
        if (grupo.getStatus() != Status.ATIVO) {
            throw new IllegalArgumentException("O grupo deve estar ativo.");
        }



        if (membrosGrupo.getRole() == null) {
            membrosGrupo.setRole(TipoUsuario.MEMBRO);
        }
        return membrosGrupoRepository.save(membrosGrupo);
    }

    @Override
    public MembrosGrupo buscarPorId(int id) {
        return membrosGrupoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<MembrosGrupo> listarTodos() {
        return membrosGrupoRepository.findAll();
    }

    @Override
    public void deletar(int id) {
    membrosGrupoRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return membrosGrupoRepository.existsById(id);
    }

    @Override
    public List<MembrosGrupo> buscarPorGrupo(int grupoId) {
        return membrosGrupoRepository.findByGrupo_Id( grupoId);
    }

    @Override
    public List<MembrosGrupo> buscarPorUsuario(int usuarioId) {
        return membrosGrupoRepository.findByUsuario_Id(usuarioId);
    }

    public List<MembrosGrupo> buscarPorStatus(Status status) {
        return membrosGrupoRepository.findByStatus(status);
    }

    @Override
    public MembrosGrupo buscarPorGrupoEUsuario(Grupo grupo, Usuario usuario) {
        return membrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);
    }

    @Override
    public List<MembrosGrupo> buscarPorRole(TipoUsuario role) {
        return membrosGrupoRepository.findByRole(role);
    }
}
