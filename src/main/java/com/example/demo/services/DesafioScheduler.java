package com.example.demo.services;

import com.example.demo.applications.DesafiosApplication;
import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DesafioScheduler {

    private final DesafiosApplication desafiosApplication;

    public DesafioScheduler(DesafiosApplication desafiosApplication) {
        this.desafiosApplication = desafiosApplication;
    }

    @Scheduled(cron = "0 0 * * * *") // Todo começo da hora (minuto 0, segundo 0)
    public void encerrarDesafiosExpirados() {
        List<Desafio> desafiosAtivos = desafiosApplication.buscarPorStatus(Status.ATIVO);
        LocalDate hoje = LocalDate.now();

        for (Desafio desafio : desafiosAtivos) {
            if (desafio.getDataFim() != null && hoje.isAfter(desafio.getDataFim())) {
                desafiosApplication.encerrarDesafio(desafio.getId());
            }
        }
    }
}
