package com.example.demo.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_recompensa", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_membro_desafio", nullable = false)
    private MembrosDesafio membroDesafio;

    @Column(name = "tipo_recompensa", nullable = false)
    private String tipoRecompensa;

    @Column(name = "valor_recompensa")
    private BigDecimal valorRecompensa;

    @Column(name = "data_recompensa", nullable = false)
    private LocalDate dataRecompensa;

    public Recompensa() {}

    public Recompensa(MembrosDesafio desafio, String tipoRecompensa, BigDecimal valorRecompensa, LocalDate dataRecompensa) {
        this.membroDesafio = desafio;
        this.tipoRecompensa = tipoRecompensa;
        this.valorRecompensa = valorRecompensa;
        this.dataRecompensa = dataRecompensa;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MembrosDesafio getDesafio() {
        return membroDesafio;
    }

    public void setDesafio(MembrosDesafio desafio) {
        this.membroDesafio = desafio;
    }

    public String getTipoRecompensa() {
        return tipoRecompensa;
    }

    public void setTipoRecompensa(String tipoRecompensa) {
        this.tipoRecompensa = tipoRecompensa;
    }

    public BigDecimal getValorRecompensa() {
        return valorRecompensa;
    }

    public void setValorRecompensa(BigDecimal valorRecompensa) {
        this.valorRecompensa = valorRecompensa;
    }

    public LocalDate getDataRecompensa() {
        return dataRecompensa;
    }

    public void setDataRecompensa(LocalDate dataRecompensa) {
        this.dataRecompensa = dataRecompensa;
    }
}