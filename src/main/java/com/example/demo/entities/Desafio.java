package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoDesafio;
import com.example.demo.records.CategoriaRecord;
import com.example.demo.records.GrupoRecord;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "desafios")
public class Desafio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_Fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "recompensa")
    private String recompensa;

    @Column(name = "is_publico")
    private Boolean isPublico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desafio")
    private TipoDesafio tipoDesafio;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador")
    private Patrocinador patrocinador;

    @OneToMany(mappedBy = "desafio")
    private List<MembrosDesafio> membrosDesafios;

    public Desafio(int id, String nome, String descricao, Categoria categoria, Grupo grupo, LocalDate dataInicio, LocalDate dataFim, Status status, String recompensa, Boolean isPublico, TipoDesafio tipoDesafio, Patrocinador patrocinador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.grupo = grupo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.recompensa = recompensa;
        this.isPublico = isPublico;
        this.tipoDesafio = tipoDesafio;
        this.patrocinador = patrocinador;
    }

    public Desafio() {
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

    public CategoriaRecord getCategoria() {
        return new CategoriaRecord(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public GrupoRecord getGrupos() {
        return new GrupoRecord(grupo.getId(), grupo.getNome(), grupo.getDescricao(), grupo.getUrlFoto(), grupo.getDataCriacao(), grupo.getStatus(), grupo.getCriador());
    }

    public void setGrupos(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public TipoDesafio getTipoDesafio() {
        return tipoDesafio;
    }

    public void setTipoDesafio(TipoDesafio tipoDesafio) {
        this.tipoDesafio = tipoDesafio;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public List<MembrosDesafio> getMembrosDesafios() {
        return membrosDesafios;
    }

    public void setMembrosDesafios(List<MembrosDesafio> membrosDesafios) {
        this.membrosDesafios = membrosDesafios;
    }
}
