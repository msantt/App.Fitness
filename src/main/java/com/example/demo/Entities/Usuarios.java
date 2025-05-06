package com.example.demo.Entities;

import com.example.demo.Enum.Objetivo;
import com.example.demo.Enum.Status;
import com.example.demo.Enum.TipoUsuario;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuarios {
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
    private Date dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "exibir_historico")
    private Boolean exibirHistorico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "criador")
    private List<Grupos> gruposCriados;

    @OneToMany(mappedBy = "usuario")
    private List<CheckIn> checkIns;

    @OneToMany(mappedBy = "usuario")
    private List<MembrosGrupo> membrosGrupos;



    public Usuarios(int id, String nome, String email, String senha, Date dataNascimento, Objetivo objetivo, String urlFoto, Date dataCriacao, Status status, Boolean exibirHistorico, TipoUsuario tipoUsuario) {
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

    public Usuarios() {

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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
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

    public List<Grupos> getGruposCriados() {
        return gruposCriados;
    }

    public void setGruposCriados(List<Grupos> gruposCriados) {
        this.gruposCriados = gruposCriados;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public List<MembrosGrupo> getMembrosGrupos() {
        return membrosGrupos;
    }

    public void setMembrosGrupos(List<MembrosGrupo> membrosGrupos) {
        this.membrosGrupos = membrosGrupos;
    }
}

