package com.example.demo.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "membros_grupo")

public class MembrosGrupo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupos grupo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "role")
    private String role;

    public MembrosGrupo(int id, Grupos grupo, Usuarios usuario, Boolean status, LocalDate dataEntrada, String role) {
        this.id = id;
        this.grupo = grupo;
        this.usuario = usuario;
        this.status = status;
        this.dataEntrada = dataEntrada;
        this.role = role;
    }

    public MembrosGrupo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos idGrupo) {
        this.grupo = idGrupo;
    }

    public Usuarios getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.usuario = idUsuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
