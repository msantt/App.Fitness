package com.example.demo.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_criador")
    private Usuarios idCriador;

    @OneToMany(mappedBy = "grupos")
    private List<Desafios> desafios;

    @OneToMany(mappedBy = "grupo")
    private List<MembrosGrupo> membros;

    public Grupos(int id, String nome, String descricao, String urlFoto, LocalDate dataCriacao, Boolean status, Usuarios idCriador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.idCriador = idCriador;
    }

    public List<MembrosGrupo> getMembros() {
        return membros;
    }

    public void setMembros(List<MembrosGrupo> membros) {
        this.membros = membros;
    }

    public Grupos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuarios getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(Usuarios idCriador) {
        this.idCriador = idCriador;
    }

    public List<Desafios> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<Desafios> desafios) {
        this.desafios = desafios;
    }


}
