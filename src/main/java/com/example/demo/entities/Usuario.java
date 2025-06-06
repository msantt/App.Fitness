package com.example.demo.entities;

import com.example.demo.enums.Objetivo;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "objetivo")
    private Objetivo objetivo;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "exibir_historico")
    private Boolean exibirHistorico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "criador")
    private List<Grupo> grupoCriados;

    @OneToMany(mappedBy = "usuario")
    private List<MembrosDesafio> desafios;

    @OneToMany(mappedBy = "usuario")
    private List<MembrosGrupo> membrosGrupos;



    public Usuario(UUID uuid, String nome, String email, String senha,UserRole role, Date dataNascimento, Objetivo objetivo, String urlFoto, LocalDateTime dataCriacao, Status status, Boolean exibirHistorico, TipoUsuario tipoUsuario) {
        this.uuid = uuid;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.dataNascimento = dataNascimento;
        this.objetivo = objetivo;
        this.urlFoto = urlFoto;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.exibirHistorico = exibirHistorico;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {

    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getExibirHistorico() {
        return exibirHistorico;
    }

    public void setExibirHistorico(Boolean exibirHistorico) {
        this.exibirHistorico = exibirHistorico;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Grupo> getGruposCriados() {
        return grupoCriados;
    }

    public void setGruposCriados(List<Grupo> grupoCriados) {
        this.grupoCriados = grupoCriados;
    }

    public List<MembrosGrupo> getMembrosGrupos() {
        return membrosGrupos;
    }

    public void setMembrosGrupos(List<MembrosGrupo> membrosGrupos) {
        this.membrosGrupos = membrosGrupos;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

