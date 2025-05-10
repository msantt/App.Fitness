package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.records.DesafiosRecord;
import com.example.demo.records.UsuariosRecord;
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
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_desafio")
    private Desafio desafio;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "local")
    private String local;

    @Column(name = "data_hora_checkin")
    private LocalDateTime dataHoraCheckin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public CheckIn(int id, Usuario usuario, Desafio desafio, String urlFoto, String local, LocalDateTime dataHoraCheckin, Status status) {
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

    public UsuariosRecord getUsuario() {
        return new UsuariosRecord(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),usuario.getDataNascimento(), usuario.getObjetivo(), usuario.getUrlFoto(), usuario.getDataCriacao(),usuario.getStatus(),usuario.getExibirHistorico());
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DesafiosRecord getDesafio() {
        return new DesafiosRecord(desafio.getId(), desafio.getNome(), desafio.getDescricao(),desafio.getCategoria(),desafio.getGrupos(),desafio.getDataInicio(),desafio.getDataFim(),desafio.getStatus(),desafio.getRecompensa(),desafio.getIsPublico(),desafio.getTipoDesafio(),desafio.getPatrocinador());
    }

    public void setDesafio(Desafio desafio) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
