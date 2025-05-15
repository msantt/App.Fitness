package com.example.demo.entities;

import com.example.demo.enums.Status;
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
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dataInicio;
    private LocalDate dataConclusao;

    @OneToMany(mappedBy = "membroDesafio", cascade = CascadeType.ALL)
    private List<CheckIn> checkins;


    public MembrosDesafio(int id, Usuario usuario, Desafio desafio, Status status, LocalDate dataConclusao) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.status = status;
        this.dataInicio = LocalDate.now();
        this.dataConclusao = dataConclusao;
    }

    public MembrosDesafio() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuariosRecord getUsuario() {
        return new UsuariosRecord(
                usuario.getId(), usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(),
                usuario.getDataNascimento(), usuario.getObjetivo(),
                usuario.getUrlFoto(), usuario.getDataCriacao(),
                usuario.getStatus(),usuario.getExibirHistorico()
        );
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DesafiosRecord getDesafio() {
        return new DesafiosRecord(
                desafio.getId(),desafio.getNome(),
                desafio.getDescricao(),
                desafio.getCategoria(),desafio.getGrupos(),
                desafio.getDataInicio(),desafio.getDataFim(),
                desafio.getStatus(),desafio.getRecompensa(),
                desafio.getIsPublico(),desafio.getTipoDesafio(),
                desafio.getPatrocinador()
        );
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
