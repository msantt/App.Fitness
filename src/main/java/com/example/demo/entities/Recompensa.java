package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recompensa")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_desafio", nullable = false)
    private Desafio desafio;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_recompensa", nullable = false)
    private String tipoRecompensa;

    @Column(name = "data_recompensa", nullable = false)
    private LocalDate dataRecompensa;

    public Recompensa() {
    }

    public Recompensa(int id, Desafio desafio, Usuario usuario, String tipoRecompensa, LocalDate dataRecompensa) {
        this.id = id;
        this.desafio = desafio;
        this.usuario = usuario;
        this.tipoRecompensa = tipoRecompensa;
        this.dataRecompensa = dataRecompensa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
