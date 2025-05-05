package com.example.demo.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "desafios")
public class Desafios {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupos grupos;


    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_Fim")
    private Date dataFim;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "recompensa")
    private String recompensa;

    @Column(name = "is_publico")
    private Boolean isPublico;

    @OneToMany(mappedBy = "desafio")
    private List<CheckIn> checkIns;

    public Desafios(int id, String nome, String descricao, Categoria categoria, Grupos grupos, Date dataInicio, Date dataFim, Boolean status, String recompensa, Boolean isPublico) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.grupos = grupos;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.recompensa = recompensa;
        this.isPublico = isPublico;
    }

    public Desafios() {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public Boolean getIsPublico() {
        return isPublico;
    }

    public void setIsPublico(Boolean isPublico) {
        this.isPublico = isPublico;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }


}
