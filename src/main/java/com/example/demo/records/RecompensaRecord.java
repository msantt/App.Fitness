package com.example.demo.records;

import java.time.LocalDate;

public record RecompensaRecord(
        int id,
        int idDesafio,
        int idUsuario,
        String tipoRecompensa,
        LocalDate dataRecompensa
) {
}
