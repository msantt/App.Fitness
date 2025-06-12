package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Alteração: Mapear UUID para VARCHAR(36) no MySQL
    @Column(name = "id_recompensa", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_membro_desafio", nullable = false)
    private MembrosDesafio membroDesafio;

    @Column(name = "tipo_recompensa", nullable = false)
    private String tipoRecompensa;

    @Column(name = "data_recompensa", nullable = false)
    private LocalDate dataRecompensa;

    public Recompensa() {
        // Gerar um UUID automaticamente ao criar uma nova instância se não for passado
        this.id = UUID.randomUUID();
    }

    public Recompensa(UUID id, MembrosDesafio desafio, String tipoRecompensa, LocalDate dataRecompensa) {
        // Se um ID for passado, use-o; caso contrário, gere um novo UUID
        this.id = (id == null) ? UUID.randomUUID() : id;
        this.membroDesafio = desafio;
        this.tipoRecompensa = tipoRecompensa;
        this.dataRecompensa = dataRecompensa;
    }

    // Getters e Setters
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

    public LocalDate getDataRecompensa() {
        return dataRecompensa;
    }

    public void setDataRecompensa(LocalDate dataRecompensa) {
        this.dataRecompensa = dataRecompensa;
    }
}