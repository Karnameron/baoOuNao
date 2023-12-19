package br.iftm.edu.baoOuNao.domain.model.usuario;


import br.iftm.edu.baoOuNao.domain.model.usuario.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String tipo;
    private String email;
    private int limiteDeLike; // Limite 3
    private boolean ativo;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;
    @PrePersist
    public void padrao(){
        ativo = true;
        limiteDeLike = 3;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
        //List.of(new SimpleGrantedAuthority("ROLE_USER"));//

    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
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
