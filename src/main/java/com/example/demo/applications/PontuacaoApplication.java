package com.example.demo.applications;

    import com.example.demo.entities.Pontuacao;
    import com.example.demo.interfaces.IPontuacao;
    import com.example.demo.repositories.PontuacaoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.UUID;

    @Service
    public class PontuacaoApplication implements IPontuacao {

        private final PontuacaoRepository pontuacaoRepository;

        @Autowired
        public PontuacaoApplication(PontuacaoRepository pontuacaoRepository) {
            this.pontuacaoRepository = pontuacaoRepository;
        }

        @Override
        public Pontuacao salvarPontuacao(Pontuacao pontuacao) {
            return pontuacaoRepository.save(pontuacao);
        }

        @Override
        public void deletarPontuacao(UUID id) {
            pontuacaoRepository.deleteById(id);
        }

        @Override
        public Pontuacao atualizarPontuacao(UUID id, Pontuacao pontuacao) {
            if (pontuacaoRepository.existsById(id)) {
                pontuacao.setId(id);
                return pontuacaoRepository.save(pontuacao);
            }
            return null;
        }

        @Override
        public List<Pontuacao> listarPontuacoes() {
            return pontuacaoRepository.findAll();
        }

        @Override
        public Pontuacao buscarPontuacaoPorUUID(UUID id) {
            return pontuacaoRepository.findById(id).orElse(null);
        }

        @Override
        public Pontuacao buscarPontuacaoPorMembroDesafio(UUID membroDesafioId) {
            return pontuacaoRepository.findByMembroDesafioUuid(membroDesafioId);
        }

        public List<Pontuacao> rankingPorDesafio(UUID desafioId) {
            List<Pontuacao> pontuacoes = pontuacaoRepository.findByMembroDesafio_Desafio_Uuid(desafioId);
            pontuacoes.sort((p1, p2) -> Integer.compare(p2.getPontuacao(), p1.getPontuacao()));
            return pontuacoes;
        }
    }