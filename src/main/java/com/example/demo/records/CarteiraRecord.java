package com.example.demo.records;

import java.time.LocalDateTime;

public record CarteiraRecord(int idCarteira, int idUsuario, double saldoAtual, LocalDateTime dataUltimaAtualizacao) {
}
