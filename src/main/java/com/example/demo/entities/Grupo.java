package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoPrivacidade;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

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
    private Usuario criador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_grupo")
    private TipoPrivacidade tipoGrupo;
    
    @Column(name = "codigo_acesso")
    private String codigoAcesso;

    @OneToMany(mappedBy = "grupo")
    private List<Desafio> desafios;

    @OneToMany(mappedBy = "grupo")
    private List<MembrosGrupo> membros;

    public Grupo() {}

    public Grupo(UUID uuid, String nome, String descricao, String urlFoto, LocalDate dataCriacao, Status status, Usuario criador, TipoPrivacidade tipoGrupo, String codigoAcesso) {
        this.uuid = uuid;
        this.nome = nome;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.criador = criador;
        this.tipoGrupo = tipoGrupo;
        this.codigoAcesso = codigoAcesso;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
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

    public TipoPrivacidade getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoPrivacidade tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public String getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(String codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    public List<Desafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<Desafio> desafios) {
        this.desafios = desafios;
    }

    public List<MembrosGrupo> getMembros() {
        return membros;
    }

    public void setMembros(List<MembrosGrupo> membros) {
        this.membros = membros;
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

    public void setCriador(Usuario idCriador) {
        this.criador = idCriador;
    }
}
