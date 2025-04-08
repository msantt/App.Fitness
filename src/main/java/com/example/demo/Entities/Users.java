package com.example.demo.Entities;

import org.hibernate.persister.entity.EntityNameUse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Users {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String objetivo;
    private String fotoPerfil;
    private LocalDateTime dataCriacao;
    private Status status;
    private boolean exibirHistoricoPublico;

    public Users(int id, String nome, String email, String senha, LocalDate dataNascimento, String objetivo, String fotoPerfil, LocalDateTime dataCriacao, Status status, boolean exibirHistoricoPublico) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.objetivo = objetivo;
        this.fotoPerfil = fotoPerfil;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.exibirHistoricoPublico = exibirHistoricoPublico;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExibirHistoricoPublico() {
        return exibirHistoricoPublico;
    }

    public void setExibirHistoricoPublico(boolean exibirHistoricoPublico) {
        this.exibirHistoricoPublico = exibirHistoricoPublico;
    }

}

//2. Grupos
//Tabela que armazena as informações dos grupos aos quais os usuários pertencem.
//id (PK)
//nome_grupo
//descricao
//foto_grupo_url (opcional - alterado de "foto" para armazenar o link da foto)
//data_criacao
//id_usuario_criador (FK - referência ao usuário que criou o grupo)
//3. Membros do Grupo
//Tabela de associação entre usuários e grupos. Um usuário pode pertencer a vários grupos.
//id_usuario (FK)
//id_grupo (FK)
//status_recidente (booleano)
//papel (admin, membro, etc.)