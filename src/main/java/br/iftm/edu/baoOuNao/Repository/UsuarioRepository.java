package br.iftm.edu.baoOuNao.Repository;

import br.iftm.edu.baoOuNao.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository  <Usuario, Long>{

}

