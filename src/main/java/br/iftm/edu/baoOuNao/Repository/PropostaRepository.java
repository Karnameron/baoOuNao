package br.iftm.edu.baoOuNao.Repository;

import br.iftm.edu.baoOuNao.domain.model.proposta.Categoria;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    int countPropostaByUsuarioAndCategoria(Usuario usuario, Categoria categoria);
    int countPropostaByUsuario(Usuario usuario);

    List<Proposta> findAllByUsuario(Usuario usuario);
    List<Proposta> findAllByCategoria(Categoria categoria);

}
