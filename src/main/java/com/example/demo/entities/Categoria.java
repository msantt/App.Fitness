package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Desafio> desafios;

    public Categoria(UUID uuid, String nome, String descricao) {
        this.uuid = uuid;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Categoria() {

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

    public List<Desafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(List<Desafio> desafios) {
        this.desafios = desafios;
    }


}
