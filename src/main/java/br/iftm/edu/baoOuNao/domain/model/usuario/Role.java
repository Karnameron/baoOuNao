package br.iftm.edu.baoOuNao.domain.model.usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.iftm.edu.baoOuNao.domain.model.usuario.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMINISTRATOR(
            Set.of(
             ADMIN_UPDATE,
             ADMIN_READ,
             ADMIN_DELETE,
             ADMIN_CREATE_PROPOSAL,
             ADMIN_CREATE_USER,
             MODERATOR_REVIEW
            )
    ),
    MODERATOR(
            Set.of(
                MODERATOR_DELETE,
                MODERATOR_READ,
                MODERATOR_CREATE_PROPOSAL,
                MODERATOR_UPDATE,
                MODERATOR_REVIEW
            )
    ),
    USER(
            Set.of(
                    USER_DELETE,
                    USER_CREATE_PROPOSAL,
                    USER_UPDATE,
                    USER_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
