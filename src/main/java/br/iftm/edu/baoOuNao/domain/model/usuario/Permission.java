package br.iftm.edu.baoOuNao.domain.model.usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE_USER("admin:createuser"),
    ADMIN_CREATE_PROPOSAL("admin:create_proposal"),


    MODERATOR_READ("moderator:read"),
    MODERATOR_UPDATE("moderator:update"),
    MODERATOR_DELETE("moderator:delete"),
    MODERATOR_REVIEW("moderator:review"),
    MODERATOR_CREATE_PROPOSAL("moderator:create_proposal"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    USER_CREATE("user:create"),
    USER_CREATE_PROPOSAL("moderator:create_proposal");
    @Getter
    private final String permission;
}
