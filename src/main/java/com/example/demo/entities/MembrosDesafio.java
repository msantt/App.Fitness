package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "membro_desafio")
public class MembrosDesafio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_membro_usuario"),
            insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "usuario_id")
    private UUID usuarioId;

    @ManyToOne
    @JoinColumn(name = "desafio_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_membro_desafio"),
            insertable = false, updatable = false)
    private Desafio desafio;

    @Column(name = "desafio_id")
    private UUID desafioId;

    @Column(name = "pontos")
    private int pontos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TipoUsuario role;

    @OneToMany(mappedBy = "membroDesafio", cascade = CascadeType.ALL)
    private List<CheckIn> checkins;


    public MembrosDesafio(UUID uuid, Usuario usuario, Desafio desafio, Status status) {
        this.uuid = uuid;
        this.usuario = usuario;
        this.desafio = desafio;
        this.status = status;
        this.dataEntrada = LocalDate.now();
    }

    public MembrosDesafio() {

    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            this.usuarioId = usuario.getId();
        }
    }

    public UUID getDesafioId() {
        return desafioId;
    }

    public void setDesafioId(UUID desafioId) {
        this.desafioId = desafioId;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
        if (desafio != null) {
            this.desafioId = desafio.getId();
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public TipoUsuario getRole() {
        return role;
    }

    public void setRole(TipoUsuario role) {
        this.role = role;
    }

    public List<CheckIn> getCheckins() {
        return checkins;
    }

    public void setCheckins(List<CheckIn> checkins) {
        this.checkins = checkins;
    }
}
