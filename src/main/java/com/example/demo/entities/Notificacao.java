package com.example.demo.entities;


import com.example.demo.enums.TipoNotificacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "mensagem")
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoNotificacao tipo;

    @Column(name = "lida")
    private boolean lida = false;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Notificacao(UUID uuid, String mensagem,TipoNotificacao tipo, boolean lida, Usuario usuario) {
        this.uuid = uuid;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.lida = lida;
        this.usuario = usuario;
    }

    public Notificacao() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TipoNotificacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoNotificacao tipo) {
        this.tipo = tipo;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
