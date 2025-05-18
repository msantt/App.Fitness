package com.example.demo.entities;

import com.example.demo.enums.Objetivo;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "objetivo")
    private Objetivo objetivo;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "exibir_historico")
    private Boolean exibirHistorico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "criador")
    private List<Grupo> grupoCriados;

    @OneToMany(mappedBy = "usuario")
    private List<MembrosDesafio> desafios;

    @OneToMany(mappedBy = "usuario")
    private List<MembrosGrupo> membrosGrupos;



    public Usuario(int id, String nome, String email, String senha, Date dataNascimento, Objetivo objetivo, String urlFoto, LocalDateTime dataCriacao, Status status, Boolean exibirHistorico, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.objetivo = objetivo;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.exibirHistorico = exibirHistorico;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getExibirHistorico() {
        return exibirHistorico;
    }

    public void setExibirHistorico(Boolean exibirHistorico) {
        this.exibirHistorico = exibirHistorico;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Grupo> getGruposCriados() {
        return grupoCriados;
    }

    public void setGruposCriados(List<Grupo> grupoCriados) {
        this.grupoCriados = grupoCriados;
    }

    public List<MembrosGrupo> getMembrosGrupos() {
        return membrosGrupos;
    }

    public void setMembrosGrupos(List<MembrosGrupo> membrosGrupos) {
        this.membrosGrupos = membrosGrupos;
    }

    public List<MembrosDesafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<MembrosDesafio> desafios) {
        this.desafios = desafios;
    }
}

