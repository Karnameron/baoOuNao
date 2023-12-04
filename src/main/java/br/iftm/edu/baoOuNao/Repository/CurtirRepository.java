package br.iftm.edu.baoOuNao.Repository;

import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurtirRepository extends JpaRepository<Curtir,Long> {

    int countByProposta(Proposta proposta);
    int countByUsuario_Id(Long id);
    boolean existsLikeByUsuarioAndProposta(Usuario usuario, Proposta proposta);
    Curtir getReferenceByUsuarioIdAndPropostaId(Long usuarioId, Long PropostaID);




}
