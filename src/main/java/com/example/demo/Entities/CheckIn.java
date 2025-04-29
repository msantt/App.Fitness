package com.example.demo.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_desafio")
    private Desafios desafio;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "local")
    private String local;

    @Column(name = "data_hora_checkin")
    private LocalDateTime dataHoraCheckin;

    @Column(name = "status")
    private Boolean status;

    public CheckIn(int id, Usuarios usuario, Desafios desafio, String urlFoto, String local, LocalDateTime dataHoraCheckin, Boolean status) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.urlFoto = urlFoto;
        this.local = local;
        this.dataHoraCheckin = dataHoraCheckin;
        this.status = status;
    }

    public CheckIn() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Desafios getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafios desafio) {
        this.desafio = desafio;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataHoraCheckin() {
        return dataHoraCheckin;
    }

    public void setDataHoraCheckin(LocalDateTime dataHoraCheckin) {
        this.dataHoraCheckin = dataHoraCheckin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
