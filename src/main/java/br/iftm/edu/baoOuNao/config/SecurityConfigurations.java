package br.iftm.edu.baoOuNao.config;

import br.iftm.edu.baoOuNao.api.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.iftm.edu.baoOuNao.domain.model.usuario.Permission.*;
import static br.iftm.edu.baoOuNao.domain.model.usuario.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/propostas/**").hasAnyRole(ADMINISTRATOR.name(), MODERATOR.name(), USER.name())
                            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/propostas/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.GET,"/propostas/contarlike/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.GET,"/propostas/retornarCategoria/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.GET,"/propostas/situacao/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.POST,"/propostas/categoria/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.POST,"/propostas/**").hasAnyAuthority(ADMIN_CREATE_PROPOSAL.name(),MODERATOR_CREATE_PROPOSAL.name(), USER_CREATE_PROPOSAL.name())
                            .requestMatchers(HttpMethod.PUT,"/propostas/**").hasAnyAuthority(ADMIN_UPDATE.name(),MODERATOR_UPDATE.name(), USER_UPDATE.name())
                            .requestMatchers(HttpMethod.DELETE,"/propostas/**").hasAnyAuthority(ADMIN_DELETE.name(),MODERATOR_DELETE.name(), USER_DELETE.name())
                            .requestMatchers(HttpMethod.PATCH,"propostas/moderar/**").hasAuthority(MODERATOR_REVIEW.name())
                            .requestMatchers(HttpMethod.GET,"/usuarios/contar/**").hasAnyAuthority(ADMIN_READ.name(),USER_READ.name(),MODERATOR_READ.name())
                            .requestMatchers(HttpMethod.GET,"/usuarios/**").hasAnyAuthority(ADMIN_READ.name(),USER_READ.name(),MODERATOR_READ.name())
                            .requestMatchers(HttpMethod.POST,"/usuarios/**").hasAuthority(ADMIN_CREATE_USER.name())
                            .requestMatchers(HttpMethod.PUT,"/usuarios/**").hasAnyAuthority(ADMIN_UPDATE.name(),USER_UPDATE.name(),MODERATOR_UPDATE.name())
                            .requestMatchers(HttpMethod.PATCH,"/usuarios/**").hasAnyAuthority(ADMIN_UPDATE.name(),USER_UPDATE.name(),MODERATOR_UPDATE.name())
                            .requestMatchers(HttpMethod.DELETE,"/usuarios/**").hasAuthority(ADMIN_DELETE.name())
                            .requestMatchers(HttpMethod.POST,"/curtir/**").hasAnyAuthority(ADMIN_CREATE_PROPOSAL.name(),USER_CREATE_PROPOSAL.name(),MODERATOR_CREATE_PROPOSAL.name())
                            .requestMatchers(HttpMethod.GET,"/curtir/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.GET,"/curtir/contar/**").permitAll()
                            .requestMatchers(HttpMethod.DELETE,"/curtir/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/curtir/existe/**").hasAnyAuthority(ADMIN_READ.name(),MODERATOR_READ.name(), USER_READ.name())
                            .requestMatchers(HttpMethod.POST, "/login").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
