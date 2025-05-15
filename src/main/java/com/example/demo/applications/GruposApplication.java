package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IGrupos;
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
public class GruposApplication implements IGrupos {


    private final UsuarioRepository usuarioRepository;
    private GrupoRepository gruposRepository;
    private MembrosGrupoRepository membroGrupoRepository;

    @Autowired
    public GruposApplication(GrupoRepository gruposRepository, UsuarioRepository usuarioRepository,
                             MembrosGrupoRepository membroGrupoRepository) {
        this.gruposRepository = gruposRepository;
        this.usuarioRepository = usuarioRepository;
        this.membroGrupoRepository = membroGrupoRepository;
    }

    @Override
    public Grupo salvar(Grupo grupo) {
        if (grupo.getStatus() == null) {
            grupo.setStatus(Status.ATIVO);
        }

        String nome = grupo.getNome();
        if (nome.length() < 3 || nome.length() > 20) {
            throw new IllegalArgumentException("O nome do grupo deve ter entre 3 e 20 caracteres.");
        }


        if (!gruposRepository.findByNome(grupo.getNome()).isEmpty()) {
            throw new RegraNegocioException("Já existe um grupo com esse nome.");
        }
        if (grupo.getCriador() == null) {
            throw new RegraNegocioException("O grupo deve ter um criador associado.");
        }

        Grupo grupoSalvo = gruposRepository.save(grupo);


        MembrosGrupo membro = new MembrosGrupo();
        membro.setGrupo(grupoSalvo);
        Usuario usuario = usuarioRepository.getById(grupoSalvo.getCriador().getId());
        membro.setUsuario(usuario);
        membro.setStatus(Status.ATIVO);
        membro.setRole(TipoUsuario.ADMIN);
        membro.setDataEntrada(LocalDate.from(LocalDateTime.now()));

        membroGrupoRepository.save(membro);

        return grupoSalvo;
    }

    @Override
    public Grupo buscarPorId(int id) {
        return gruposRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Grupo> listarTodos() {
        return gruposRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        gruposRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return gruposRepository.existsById(id);
    }

    @Override
    public List<Grupo> buscarPorStatus(Status status) {
        return gruposRepository.findByStatus(status);
    }

    @Override
    public List<Grupo> buscarPorCriadorId(int criador) {
        return gruposRepository.findByCriadorId(criador);
    }

    @Override
    public List<Grupo> buscarPorNome(String nome) {
        return gruposRepository.findByNome(nome);
    }

}
