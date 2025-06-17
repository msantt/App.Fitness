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
    public void enviarNotificacoesFinaisDesafios() {
        LocalDate hoje = LocalDate.now();

        // 2 dias antes
        LocalDate doisDiasDepois = hoje.plusDays(2);
        List<Desafio> desafiosDoisDias = desafioRepository.findByDataFim(doisDiasDepois);
        for (Desafio d : desafiosDoisDias) {
            notificarMembros(d, "Faltam 2 dias para acabar o desafio " + d.getNome());
        }

        // 1 dia antes
        LocalDate umDiaDepois = hoje.plusDays(1);
        List<Desafio> desafiosUmDia = desafioRepository.findByDataFim(umDiaDepois);
        for (Desafio d : desafiosUmDia) {
            notificarMembros(d, "⚠️ Faltam **1 dia** para acabar o desafio: " + d.getNome());
        }

        // Dia do término
        List<Desafio> desafiosHoje = desafioRepository.findByDataFim(hoje);
        for (Desafio d : desafiosHoje) {
            notificarMembros(d, "⏳ O desafio **" + d.getNome() + "** termina **HOJE!** Finalize seus check-ins!");
        }
    }

    private void notificarMembros(Desafio desafio, String mensagem) {
        List<MembrosDesafio> membros = membroDesafioRepository.findByDesafioUuid(desafio.getId());
        for (MembrosDesafio membro : membros) {
            notificacaoService.notificarUsuario(membro.getUsuario(), mensagem, TipoNotificacao.ALERTA_TEMPO);
        }
    }
}

