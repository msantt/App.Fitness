package com.example.demo.Applications;

import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Interfaces.IMembrosGrupo;
import com.example.demo.Repositories.MembrosGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembrosGrupoApplication implements IMembrosGrupo {



    private MembrosGrupoRepository membrosGrupoRepository;

    @Autowired
    public MembrosGrupoApplication(MembrosGrupoRepository membrosGrupoRepository) {
        this.membrosGrupoRepository = membrosGrupoRepository;
    }

    @Override
    public MembrosGrupo salvar(MembrosGrupo membrosGrupo) {
        return membrosGrupoRepository.save(membrosGrupo);
    }

    @Override
    public Optional<MembrosGrupo> buscarPorId(int id) {
        return membrosGrupoRepository.findById(id);
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

    @Override
    public List<MembrosGrupo> buscarPorStatus(Boolean status) {
        return membrosGrupoRepository.findByStatus(status);
    }

    @Override
    public MembrosGrupo buscarPorGrupoEUsuario(Grupos grupo, Usuarios usuario) {
        return membrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);
    }

    @Override
    public List<MembrosGrupo> buscarPorRole(String role) {
        return membrosGrupoRepository.findByRole(role);
    }
}
