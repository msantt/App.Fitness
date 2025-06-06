package com.example.demo.services;

import com.example.demo.applications.NotificacaoApplication;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.repositories.DesafioRepository;
import com.example.demo.repositories.MembrosDesafioRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class NotificacaoScheduler {

    private final DesafioRepository desafioRepository;
    private final MembrosDesafioRepository membroDesafioRepository;
    private final NotificacaoApplication notificacaoService;

    public NotificacaoScheduler(
            DesafioRepository desafioRepository,
            MembrosDesafioRepository membroDesafioRepository,
            NotificacaoApplication notificacaoService
    ) {
        this.desafioRepository = desafioRepository;
        this.membroDesafioRepository = membroDesafioRepository;
        this.notificacaoService = notificacaoService;
    }

    @Scheduled(cron = "0 0 8 * * *") // Executa todos os dias às 08:00
    public void verificarDesafiosComPrazoFinal() {
        LocalDate hoje = LocalDate.now();
        LocalDate doisDiasDepois = hoje.plusDays(2);

        List<Desafio> desafios = desafioRepository.findByDataFim(doisDiasDepois);

        for (Desafio d : desafios) {
            List<MembrosDesafio> membros = membroDesafioRepository.findByDesafioUuid(d.getId());
            for (MembrosDesafio m : membros) {
                String msg = "Faltam 2 dias para acabar o desafio " + d.getNome();
                notificacaoService.notificarUsuario(m.getUsuario(), msg, TipoNotificacao.ALERTA_TEMPO);
            }
        }
    }
}
