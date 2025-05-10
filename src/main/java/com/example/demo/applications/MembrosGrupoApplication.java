package com.example.demo.applications;

import com.example.demo.entities.Grupo;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;
import com.example.demo.interfaces.IMembrosGrupo;
import com.example.demo.repositories.MembrosGrupoRepository;
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
    public List<MembrosGrupo> buscarPorRole(String role) {
        return membrosGrupoRepository.findByRole(role);
    }
}
