package com.example.demo.records;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record CheckInRecord(
        int id,
        String urlFoto,
        String local,
        LocalDateTime dataHoraCheckin,
        Status status) {
}
