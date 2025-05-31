package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.records.GrupoRecord;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "membros_grupo")

public class MembrosGrupo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TipoUsuario role;


    public MembrosGrupo(UUID uuid, Grupo grupo, Usuario usuario, Status status, LocalDate dataEntrada, TipoUsuario role) {
        this.uuid = uuid;
        this.grupo = grupo;
        this.usuario = usuario;
        this.status = status;
        this.dataEntrada = dataEntrada;
        this.role = role;
    }

    public MembrosGrupo() {
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UsuariosRecord getUsuario() {
        return new UsuariosRecord(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),usuario.getDataNascimento(), usuario.getObjetivo(), usuario.getUrlFoto(), usuario.getDataCriacao(),usuario.getStatus(),usuario.getExibirHistorico());
    }

    public void setUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    public GrupoRecord getGrupo() {
        return new GrupoRecord(grupo.getId(),grupo.getNome(), grupo.getDescricao(), grupo.getUrlFoto(),grupo.getDataCriacao(),grupo.getStatus(),grupo.getCriador());
    }

    public void setGrupo(Grupo idGrupo) {
        this.grupo = idGrupo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public TipoUsuario getRole() {
        return role;
    }

    public void setRole(TipoUsuario role) {
        this.role = role;
    }
}
