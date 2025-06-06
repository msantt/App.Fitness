package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "patrocinador")
public class Patrocinador {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "site")
    private String site;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "cnpj")
    private String cnpj;

    public Patrocinador(UUID uuid, String nome, String descricao, String site, String logoUrl, String cnpj) {
        this.uuid = uuid;
        this.nome = nome;
        this.descricao = descricao;
        this.site = site;
        this.logoUrl = logoUrl;
        this.cnpj = cnpj;
    }

    public Patrocinador() {
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
