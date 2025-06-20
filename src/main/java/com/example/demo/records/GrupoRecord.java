package com.example.demo.records;

import com.example.demo.enums.Status;
import com.example.demo.enums.TipoPrivacidade;

import java.time.LocalDate;
import java.util.UUID;

public record GrupoRecord(
        UUID id,
        String nome,
        String descricao,
        String urlFoto,
        LocalDate dataCriacao,
        Status status,
        UsuariosRecord criador,
        TipoPrivacidade tipoPrivacidade,
        String codigoAcesso
) {
}
