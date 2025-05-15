package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.records.DesafiosRecord;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_membro_desafio")
    private MembrosDesafio membroDesafio;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "local")
    private String local;

    @Column(name = "data_hora_checkin")
    private LocalDateTime dataHoraCheckin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public CheckIn(int id, MembrosDesafio membroDesafio, String urlFoto, String local, Status status) {
        this.id = id;
        this.membroDesafio = membroDesafio;
        this.urlFoto = urlFoto;
        this.local = local;
        this.dataHoraCheckin = LocalDateTime.now();
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

    public MembrosDesafio getMembroDesafio() {
        return membroDesafio;
    }

    public void setMembroDesafio(MembrosDesafio membroDesafio) {
        this.membroDesafio = membroDesafio;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
