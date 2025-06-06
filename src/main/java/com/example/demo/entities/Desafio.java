package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoDesafio;
import com.example.demo.records.CategoriaRecord;
import com.example.demo.records.GrupoRecord;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "desafios")
public class Desafio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

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

    @Column(name = "valor_aposta")
    private String valorAposta;

    @Column(name = "is_publico")
    private Boolean isPublico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desafio")
    private TipoDesafio tipoDesafio;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador")
    private Patrocinador patrocinador;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @OneToMany(mappedBy = "desafio")
    private List<MembrosDesafio> membrosDesafios;

    public Desafio(UUID uuid, String nome, String descricao, Categoria categoria, Grupo grupo, LocalDate dataInicio, LocalDate dataFim, Status status, String valorAposta, Boolean isPublico, TipoDesafio tipoDesafio, Patrocinador patrocinador,Usuario criador) {
        this.uuid = uuid;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.grupo = grupo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.valorAposta = valorAposta;
        this.isPublico = isPublico;
        this.tipoDesafio = tipoDesafio;
        this.patrocinador = patrocinador;
        this.criador = criador;
    }

    public Desafio() {
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

    public String getValorAposta() {
        return valorAposta;
    }

    public void setValorAposta(String valorAposta) {
        this.valorAposta = valorAposta;
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

    public void setMembrosDesafios(List<MembrosDesafio> membrosDesafios) {
        this.membrosDesafios = membrosDesafios;
    }

    public UsuariosRecord getCriador() {
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

    public void setCriador(Usuario usuario) {
        this.criador = usuario;
    }
}
