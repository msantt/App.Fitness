package com.example.demo.infra.security;

import com.example.demo.entities.Usuario;
import com.example.demo.facades.UsuariosFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuariosFacade usuariosFacade;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody @Valid RegisterDTO data) {
        if (usuariosFacade.existePorEmail(data.email())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(data.nome());
        novoUsuario.setEmail(data.email());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(data.role());
        novoUsuario.setDataNascimento(data.dataNascimento());
        novoUsuario.setObjetivo(data.objetivo());
        novoUsuario.setUrlFoto(data.urlFoto());
        novoUsuario.setDataCriacao(LocalDateTime.now());
        novoUsuario.setStatus(data.status());
        novoUsuario.setExibirHistorico(data.exibirHistorico());
        novoUsuario.setTipoUsuario(data.tipoUsuario());

        usuariosFacade.salvar(novoUsuario);

        return ResponseEntity.status(201).build();
    }
}
