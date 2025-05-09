package com.example.demo.Entities;

import com.example.demo.Enum.Status;
import com.example.demo.Record.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_criador")
    private Usuarios criador;

    @OneToMany(mappedBy = "grupos")
    private List<Desafios> desafios;

    @OneToMany(mappedBy = "grupo")
    private List<MembrosGrupo> membros;

    public Grupos(int id, String nome, String descricao, String urlFoto, LocalDate dataCriacao, Status status, Usuarios criador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.criador = criador;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UsuariosRecord getCriador() {
        if (criador == null) {
            return null;
        }

        return new UsuariosRecord(
                criador.getId(),
                criador.getNome(),
                criador.getEmail(),
                criador.getSenha(),
                criador.getDataNascimento(),
                criador.getObjetivo(),
                criador.getUrlFoto(),
                criador.getDataCriacao(),
                criador.getStatus(),
                criador.getExibirHistorico()
        );
    }

    public void setCriador(Usuarios idCriador) {
        this.criador = idCriador;
    }

    public List<Desafios> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<Desafios> desafios) {
        this.desafios = desafios;
    }


}
