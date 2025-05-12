package com.example.demo.records;

import java.time.LocalDate;

public record PagamentoDesafioRecord(
        int idPagamento,
        double valor,
        String metodoPagamento,
        String statusPagamento,
        LocalDate dataPagamento,
        String transacaoId,
        int idUsuario,
        int idDesafio
) {
}
