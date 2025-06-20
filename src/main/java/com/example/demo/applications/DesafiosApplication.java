package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.*;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoNotificacao;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.interfaces.IDesafios;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DesafiosApplication implements IDesafios {


    private final GrupoRepository grupoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MembrosDesafioRepository membrosDesafioRepository;
    private final MembrosGrupoRepository membrosGrupoRepository;
    private final PontuacaoRepository pontuacaoRepository;
    private final RecompensaApplication recompensaApplication;
    private final MembrosDesafiosApplication membrosDesafiosApplication;
    private final PagamentosDesafioApplication pagamentosDesafioApplication;
    private NotificacaoApplication notificacaoApplication;
    private DesafioRepository desafiosRepository;
    private UsuariosApplication usuariosApplication;

    @Autowired
    public DesafiosApplication(DesafioRepository desafiosRepository, GrupoRepository grupoRepository, CategoriaRepository categoriaRepository, UsuariosApplication usuariosApplication, MembrosDesafioRepository membrosDesafioRepository, MembrosGrupoRepository membrosGrupoRepository, NotificacaoApplication notificacaoApplication, PontuacaoRepository pontuacaoRepository, PagamentosDesafioApplication pagamentosDesafioApplication, RecompensaApplication recompensaApplication, MembrosDesafiosApplication membrosDesafiosApplication) {
        this.desafiosRepository = desafiosRepository;
        this.grupoRepository = grupoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuariosApplication = usuariosApplication;
        this.membrosDesafioRepository = membrosDesafioRepository;
        this.membrosGrupoRepository = membrosGrupoRepository;
        this.notificacaoApplication = notificacaoApplication;
        this.pontuacaoRepository = pontuacaoRepository;
        this.recompensaApplication = recompensaApplication;
        this.membrosDesafiosApplication = membrosDesafiosApplication;
        this.pagamentosDesafioApplication = pagamentosDesafioApplication;
    }

    @Override
    public Desafio salvar(Desafio desafio) {
        if (desafio.getStatus() == null) {
            desafio.setStatus(Status.ATIVO);
        }
        if (desafio.getDataInicio().isBefore(LocalDate.now())) {
            throw new RegraNegocioException("A data de início deve ser futura.");
        }

        if (desafio.getDataFim().isBefore(desafio.getDataInicio().plusDays(3))) {
            throw new RegraNegocioException("O desafio deve ter no mínimo 3 dias de duração.");
        }


        if (desafio.getCodigo() == null || desafio.getCodigo().isEmpty()) {
            CodigoDesafioGenerator codigoDesafioGenerator = new CodigoDesafioGenerator();
            desafio.setCodigo(codigoDesafioGenerator.gerarCodigoUnico(8, "DSF"));
        }


        if (desafio.getGrupos() == null) {
            throw new RegraNegocioException("O desafio deve estar associado a um grupo.");
        }

        Grupo grupo = grupoRepository.findByUuid(desafio.getGrupos().id());

        if (grupo.getStatus() != Status.ATIVO) {
            throw new RegraNegocioException("O grupo do desafio deve estar ativo.");
        }

        Categoria categoria = categoriaRepository.findByUuid(desafio.getCategoria().id());

        if (categoria == null) {
            throw new RegraNegocioException("O desafio deve ter uma categoria.");
        }

        boolean nomeRepetido = existePorNome(desafio.getNome(), desafio);

        if (nomeRepetido) {
            throw new RegraNegocioException("Já existe um desafio com esse nome no grupo.");
        }
        Desafio desafioSalvo = desafiosRepository.save(desafio);

        Usuario usuario = usuariosApplication.buscarPorUUID(desafioSalvo.getCriador().getId());
        BigDecimal valorAposta = desafioSalvo.getValorAposta();
        if (usuario.getSaldo().compareTo(valorAposta) < 0) {
            throw new RegraNegocioException("Saldo insuficiente para criar e participar do desafio.");
        }
        usuario.setSaldo(usuario.getSaldo().subtract(valorAposta));
        usuariosApplication.update(usuario);


        PagamentoDesafio pagamento = new PagamentoDesafio(
                valorAposta.doubleValue(),
                "saldo", // ou outro método
                "pago",
                LocalDate.now(),
                usuario,
                desafioSalvo
        );
        pagamentosDesafioApplication.salvar(pagamento);

        MembrosDesafio membro = new MembrosDesafio();
        membro.setDesafio(desafioSalvo);
        Usuario usuario1 = usuariosApplication.buscarPorUUID(desafioSalvo.getCriador().getId());
        membro.setUsuario(usuario1);
        membro.setStatus(Status.ATIVO);
        membro.setRole(TipoUsuario.ADMIN);
        membro.setDataEntrada(LocalDate.from(LocalDateTime.now()));

        membrosDesafiosApplication.salvar(membro);
        List<MembrosGrupo> membrosGrupo = membrosGrupoRepository.findByGrupo_Uuid(grupo.getId());
        Usuario criador = usuario;
        for (MembrosGrupo membroGrupo : membrosGrupo) {
            Usuario u = usuariosApplication.buscarPorUUID(membroGrupo.getUsuario().getId());
            if (!u.getId().equals(criador.getId())) {
                String msg = criador.getNome() + " criou um novo desafio no grupo " + grupo.getNome() + ": " + desafio.getNome();
                notificacaoApplication.notificarUsuario(u, msg, TipoNotificacao.NOVO_DESAFIO);
            }
        }
        return desafioSalvo;
    }

    public class CodigoDesafioGenerator {

        private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final SecureRandom random = new SecureRandom();

        public String gerarCodigoDesafio(int tamanho, String prefixo) {
            StringBuilder codigo = new StringBuilder(tamanho);
            for (int i = 0; i < tamanho; i++) {
                int index = random.nextInt(CARACTERES.length());
                codigo.append(CARACTERES.charAt(index));
            }
            return (prefixo != null && !prefixo.isEmpty() ? prefixo + "-" : "") + codigo.toString();
        }

        public String gerarCodigoUnico(int tamanho, String prefixo) {
            String codigo;
            do {
                codigo = gerarCodigoDesafio(tamanho, prefixo);
            } while (desafiosRepository.existsByCodigo(codigo));
            return codigo;
        }
    }

    @Override
    public Desafio buscarPorUUID(UUID id) {
        return desafiosRepository.findByUuid(id);
    }

    @Override
    public List<Desafio> listarTodos() {
        return desafiosRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        desafiosRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return desafiosRepository.existsByUuid(id);
    }

    @Override
    public List<Desafio> buscarPorUUIDGrupo(UUID idGrupo) {
        return desafiosRepository.findByGrupo_Uuid(idGrupo);
    }

    @Override
    public List<Desafio> buscarPorUUIDCategoria(UUID idCategoria) {
        return desafiosRepository.findByCategoria_Uuid(idCategoria);
    }

    @Override
    public List<Desafio> buscarPorStatus(Status status) {
        return desafiosRepository.findByStatus(status);
    }

    public boolean existePorNome(String nome, Desafio desafio) {
        return desafiosRepository
                .findByGrupo_Uuid(desafio.getGrupos().id())
                .stream()
                .anyMatch(d -> d.getNome().equalsIgnoreCase(desafio.getNome()));
    }

    @Override
    @Transactional
    public void encerrarDesafio(UUID desafioUuid) {
        Desafio desafio = desafiosRepository.findByUuid(desafioUuid);
        if (desafio == null) throw new RegraNegocioException("Desafio não encontrado.");

        desafio.setStatus(Status.INATIVO);
        desafiosRepository.save(desafio);

        List<MembrosDesafio> membros = membrosDesafioRepository.findByDesafioUuid(desafioUuid);
        for (MembrosDesafio membro : membros) {
            notificacaoApplication.notificarUsuario(
                    membro.getUsuario(),
                    "O desafio " + desafio.getNome() + " foi encerrado.",
                    TipoNotificacao.DESAFIO_ENCERRADO
            );
        }

        List<Pontuacao> ranking = pontuacaoRepository.findByMembroDesafio_Desafio_UuidOrderByPontuacaoDesc(desafioUuid);

        // lógica de desempate: 1º critério - pontuação, 2º critério - dias consecutivos, 3º critério - data do último check-in
        if (ranking.size() > 1) {
            ranking.sort((p1, p2) -> {
                int cmpPontuacao = Integer.compare(p2.getPontuacao(), p1.getPontuacao());
                if (cmpPontuacao != 0) {
                    return cmpPontuacao;
                }
                int cmpDias = Integer.compare(p2.getDiasConsecutivos(), p1.getDiasConsecutivos());
                if (cmpDias != 0) {
                    return cmpDias;
                }
                return p2.getDataUltimoCheckin().compareTo(p1.getDataUltimoCheckin());
            });
        }

        if (ranking.size() == 1) {
            // Apenas um participante: recebe 100%
            Pontuacao primeiro = ranking.get(0);
            Usuario usuarioPrimeiro = primeiro.getMembroDesafio().getUsuario();
            BigDecimal valorTotal = desafio.getValorAposta();
            usuarioPrimeiro.setSaldo(usuarioPrimeiro.getSaldo().add(valorTotal));
            usuariosApplication.update(usuarioPrimeiro);
            notificacaoApplication.notificarUsuario(
                    usuarioPrimeiro,
                    "Parabéns! Você ficou em 1º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorTotal + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensa = new Recompensa(primeiro.getMembroDesafio(), "1º lugar", valorTotal, LocalDate.now());
            recompensaApplication.salvar(recompensa);
        } else if (ranking.size() == 2) {
            // Dois participantes: 1º lugar 75%, 2º lugar 25%
            BigDecimal valorTotal =desafio.getValorAposta().multiply(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(0.9));
            // 1º lugar
            Pontuacao primeiro = ranking.get(0);
            Usuario usuarioPrimeiro = primeiro.getMembroDesafio().getUsuario();
            BigDecimal valorPrimeiro = valorTotal.multiply(BigDecimal.valueOf(0.75));
            usuarioPrimeiro.setSaldo(usuarioPrimeiro.getSaldo().add(valorPrimeiro));
            usuariosApplication.update(usuarioPrimeiro);
            notificacaoApplication.notificarUsuario(
                    usuarioPrimeiro,
                    "Parabéns! Você ficou em 1º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorPrimeiro + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensa = new Recompensa(primeiro.getMembroDesafio(), "1º lugar", valorPrimeiro, LocalDate.now());
            recompensaApplication.salvar(recompensa);

            // 2º lugar
            Pontuacao segundo = ranking.get(1);
            Usuario usuarioSegundo = segundo.getMembroDesafio().getUsuario();
            BigDecimal valorSegundo = valorTotal.multiply(BigDecimal.valueOf(0.25));
            usuarioSegundo.setSaldo(usuarioSegundo.getSaldo().add(valorSegundo));
            usuariosApplication.update(usuarioSegundo);
            notificacaoApplication.notificarUsuario(
                    usuarioSegundo,
                    "Parabéns! Você ficou em 2º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorSegundo + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensaSegundo = new Recompensa(segundo.getMembroDesafio(), "2º lugar", valorSegundo, LocalDate.now());
            recompensaApplication.salvar(recompensaSegundo);
        } else if (ranking.size() >= 3) {
            BigDecimal valorTotal = desafio.getValorAposta().multiply(BigDecimal.valueOf(ranking.size())).multiply(BigDecimal.valueOf(0.9));

            // 1º lugar - 50%
            Pontuacao primeiro = ranking.get(0);
            Usuario usuarioPrimeiro = primeiro.getMembroDesafio().getUsuario();
            BigDecimal valorPrimeiro = valorTotal.multiply(BigDecimal.valueOf(0.5));
            usuarioPrimeiro.setSaldo(usuarioPrimeiro.getSaldo().add(valorPrimeiro));
            usuariosApplication.update(usuarioPrimeiro);
            notificacaoApplication.notificarUsuario(
                    usuarioPrimeiro,
                    "Parabéns! Você ficou em 1º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorPrimeiro + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensa = new Recompensa(primeiro.getMembroDesafio(), "1º lugar", valorPrimeiro, LocalDate.now());
            recompensaApplication.salvar(recompensa);

            // 2º lugar - 30%
            Pontuacao segundo = ranking.get(1);
            Usuario usuarioSegundo = segundo.getMembroDesafio().getUsuario();
            BigDecimal valorSegundo = valorTotal.multiply(BigDecimal.valueOf(0.3));
            usuarioSegundo.setSaldo(usuarioSegundo.getSaldo().add(valorSegundo));
            usuariosApplication.update(usuarioSegundo);
            notificacaoApplication.notificarUsuario(
                    usuarioSegundo,
                    "Parabéns! Você ficou em 2º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorSegundo + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensaSegundo = new Recompensa(segundo.getMembroDesafio(), "2º lugar", valorSegundo, LocalDate.now());
            recompensaApplication.salvar(recompensaSegundo);

            // 3º lugar - 20%
            Pontuacao terceiro = ranking.get(2);
            Usuario usuarioTerceiro = terceiro.getMembroDesafio().getUsuario();
            BigDecimal valorTerceiro = valorTotal.multiply(BigDecimal.valueOf(0.2));
            usuarioTerceiro.setSaldo(usuarioTerceiro.getSaldo().add(valorTerceiro));
            usuariosApplication.update(usuarioTerceiro);
            notificacaoApplication.notificarUsuario(
                    usuarioTerceiro,
                    "Parabéns! Você ficou em 3º lugar no desafio '" + desafio.getNome() +
                            "' e ganhou R$ " + valorTerceiro + ".",
                    TipoNotificacao.PREMIO_DESAFIO
            );
            Recompensa recompensaTerceiro = new Recompensa(terceiro.getMembroDesafio(), "3º lugar", valorTerceiro, LocalDate.now());
            recompensaApplication.salvar(recompensaTerceiro);
        }
    }

    @Override
    public Desafio buscarPorCodigo(String codigo) {
        return desafiosRepository.findByCodigo(codigo);
    }

    @Transactional
    public boolean cancelarDesafio(UUID desafioId, UUID usuarioId) {
        Desafio desafio = desafiosRepository.findByUuid(desafioId);
        if (desafio == null || desafio.getStatus() != Status.ATIVO) return false;
        if (!desafio.getCriador().getId().equals(usuarioId)) return false;
        if (LocalDate.now().isAfter(desafio.getDataInicio())) return false;

        List<MembrosDesafio> membros = membrosDesafioRepository.findByDesafioUuid(desafioId);
        BigDecimal valorAposta = desafio.getValorAposta();
        for (MembrosDesafio membro : membros) {
            Usuario usuario = membro.getUsuario();
            usuario.setSaldo(usuario.getSaldo().add(valorAposta));
            usuariosApplication.update(usuario);
            notificacaoApplication.notificarUsuario(
                usuario,
                "O desafio '" + desafio.getNome() + "' foi cancelado. Valor devolvido: R$ " + valorAposta,
                TipoNotificacao.DESAFIO_CANCELADO
            );
        }
        desafio.setStatus(Status.CANCELADO);
        desafiosRepository.save(desafio);
        return true;
    }
}

