package br.iftm.edu.baoOuNao.Repository;

import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurtirRepository extends JpaRepository<Curtir,Long> {

    int countByProposta(Proposta proposta);
    boolean existsLikeByPropostaAndUsuario(Proposta proposta,Usuario usuario);
}
