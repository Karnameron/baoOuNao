package br.iftm.edu.baoOuNao.Repository;

import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository  <Usuario, Long>{
    UserDetails findByLogin(String login);
}

