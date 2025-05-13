package com.example.demo.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira")
    private int idCarteira;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "saldo_atual", nullable = false)
    private BigDecimal saldoAtual;

    @Column(name = "data_ultima_atualizacao", nullable = false)
    private LocalDateTime dataUltimaAtualizacao;

    public Carteira() {
    }

    public Carteira(int idCarteira, int idUsuario, BigDecimal saldoAtual, LocalDateTime dataUltimaAtualizacao) {
        this.idCarteira = idCarteira;
        this.idUsuario = idUsuario;
        this.saldoAtual = saldoAtual;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public int getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(int idCarteira) {
        this.idCarteira = idCarteira;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }
}
