package com.example.demo.entities;

import com.example.demo.enums.StatusConvite;
import com.example.demo.enums.TipoConvite;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Convite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Usuario convidado;

    @ManyToOne
    private Usuario remetente;

    private UUID grupoOuDesafioId;

    @Enumerated(EnumType.STRING)
    private StatusConvite status;

    @Enumerated(EnumType.STRING)
    private TipoConvite tipo;

    private LocalDateTime dataEnvio;

    public Convite(Usuario convidado, Usuario remetente, UUID grupoOuDesafioId, StatusConvite status,TipoConvite tipo, LocalDateTime dataEnvio) {
        this.convidado = convidado;
        this.remetente = remetente;
        this.grupoOuDesafioId = grupoOuDesafioId;
        this.status = status;
        this.dataEnvio = dataEnvio;
    }

    public Convite() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getConvidado() {
        return convidado;
    }

    public void setConvidado(Usuario convidado) {
        this.convidado = convidado;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public UUID getGrupoOuDesafioId() {
        return grupoOuDesafioId;
    }

    public void setGrupoOuDesafioId(UUID grupoOuDesafioId) {
        this.grupoOuDesafioId = grupoOuDesafioId;
    }

    public StatusConvite getStatus() {
        return status;
    }

    public void setStatus(StatusConvite status) {
        this.status = status;
    }

    public TipoConvite getTipo() {
        return tipo;
    }

    public void setTipo(TipoConvite tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}