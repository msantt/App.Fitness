package com.example.demo.applications;

import com.example.demo.entities.Notificacao;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoApplication {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void notificarUsuario(Usuario usuario, String mensagem, TipoNotificacao tipo) {
        Notificacao notificacao = new Notificacao();
        notificacao.setUsuario(usuario);
        notificacao.setMensagem(mensagem);
        notificacao.setTipo(tipo);
        notificacaoRepository.save(notificacao);
    }
}
