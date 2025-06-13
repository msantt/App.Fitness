package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pontuacao", updatable = false, nullable = false)
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "id_membro_desafio", nullable = false, unique = true)
    private MembrosDesafio membroDesafio;

    @Column(name = "dias_consecutivos", nullable = false)
    private int diasConsecutivos;

    @Column(name = "pontuacao", nullable = false)
    private int pontuacao;

    @Column(name = "data_ultimo_checkin")
    private LocalDate dataUltimoCheckin;

    public Pontuacao() {}

    public Pontuacao(UUID id,MembrosDesafio membroDesafio, int diasConsecutivos, int pontuacao, LocalDate dataUltimoCheckin) {
        this.uuid = id;
        this.membroDesafio = membroDesafio;
        this.diasConsecutivos = diasConsecutivos;
        this.pontuacao = pontuacao;
        this.dataUltimoCheckin = dataUltimoCheckin;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID id) {
        this.uuid = id;
    }

    public MembrosDesafio getMembroDesafio() {
        return membroDesafio;
    }

    public void setMembroDesafio(MembrosDesafio membroDesafio) {
        this.membroDesafio = membroDesafio;
    }

    public int getDiasConsecutivos() {
        return diasConsecutivos;
    }

    public void setDiasConsecutivos(int diasConsecutivos) {
        this.diasConsecutivos = diasConsecutivos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDate getDataUltimoCheckin() {
        return dataUltimoCheckin;
    }

    public void setDataUltimoCheckin(LocalDate dataUltimoCheckin) {
        this.dataUltimoCheckin = dataUltimoCheckin;
    }

    public void registrarCheckin(LocalDate dataCheckin) {
        if (this.dataUltimoCheckin != null && this.dataUltimoCheckin.plusDays(1).equals(dataCheckin)) {
            this.diasConsecutivos += 1;
        } else if (this.dataUltimoCheckin == null || this.dataUltimoCheckin.isBefore(dataCheckin.minusDays(1))) {
            this.diasConsecutivos = 1;
        }
        int pontosGanhos = 10 + (this.diasConsecutivos - 1) * 2;
        this.pontuacao += pontosGanhos;
        this.dataUltimoCheckin = dataCheckin;
    }
}