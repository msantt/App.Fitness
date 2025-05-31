package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.records.DesafiosRecord;
import com.example.demo.records.MembroDesafioRecord;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

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

    public CheckIn(UUID uuid, MembrosDesafio membroDesafio, String urlFoto, String local, Status status) {
        this.uuid = uuid;
        this.membroDesafio = membroDesafio;
        this.urlFoto = urlFoto;
        this.local = local;
        this.dataHoraCheckin = LocalDateTime.now();
        this.status = status;
    }

    public CheckIn() {
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public MembroDesafioRecord getMembroDesafio() {
        return new MembroDesafioRecord(membroDesafio.getId()
                ,membroDesafio.getUsuario(),membroDesafio.getDesafio(),
                membroDesafio.getStatus());
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
