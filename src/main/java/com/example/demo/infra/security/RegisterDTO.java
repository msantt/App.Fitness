// src/main/java/com/example/demo/records/RegisterDTO.java
package com.example.demo.infra.security;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.enums.Objetivo;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.enums.UserRole;

public record RegisterDTO(
        String nome, String email, String senha, UserRole role, Date dataNascimento, Objetivo objetivo, String urlFoto, LocalDateTime dataCriacao, Status status, Boolean exibirHistorico, TipoUsuario tipoUsuario, BigDecimal saldo, String chavePix
) {}