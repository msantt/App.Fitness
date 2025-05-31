package com.example.demo.records;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckInRecord(
        UUID id,
        String urlFoto,
        String local,
        LocalDateTime dataHoraCheckin,
        Status status) {
}
