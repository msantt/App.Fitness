package com.example.demo.entities;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.records.CheckInRecord;
import com.example.demo.records.DesafiosRecord;
import com.example.demo.records.UsuariosRecord;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "membro_desafio")
public class MembrosDesafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_membro_usuario"),
            insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "usuario_id")
    private int usuarioId;

    @ManyToOne
    @JoinColumn(name = "desafio_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_membro_desafio"),
            insertable = false, updatable = false)
    private Desafio desafio;

    @Column(name = "desafio_id")
    private int desafioId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TipoUsuario role;

    @OneToMany(mappedBy = "membroDesafio", cascade = CascadeType.ALL)
    private List<CheckIn> checkins;


    public MembrosDesafio(int id, Usuario usuario, Desafio desafio, Status status) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.status = status;
        this.dataEntrada = LocalDate.now();
    }

    public MembrosDesafio() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
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

    public int getDesafioId() {
        return desafioId;
    }

    public void setDesafioId(int desafioId) {
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
