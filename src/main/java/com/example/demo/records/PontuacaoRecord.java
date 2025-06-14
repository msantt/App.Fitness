package com.example.demo.records;

import java.time.LocalDate;
import java.util.UUID;

public record PontuacaoRecord(
    UUID id,
    int pontuacao,
    int diasConsecutivos,
    LocalDate dataUltimoCheckin
) {}