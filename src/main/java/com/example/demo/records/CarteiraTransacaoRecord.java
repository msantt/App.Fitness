package com.example.demo.records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarteiraTransacaoRecord(
        int idTransacao,
        int idCarteira,
        String tipoTransacao,
        BigDecimal valor,
        String descricao,
        LocalDateTime dataTransacao
) {
}
