package br.iftm.edu.baoOuNao.Service;

import br.iftm.edu.baoOuNao.Repository.CurtirRepository;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CurtirLikeService {
    @Autowired
    CurtirRepository curtirRepository;

    @Autowired
    PropostaRepository propostaRepository;
    public Curtir salvar(Curtir curtir){

        var curtiu = usuarioCurtiu(curtir.getUsuario(),curtir.getProposta());
        System.out.println(curtiu);
        if(!curtiu){
            return curtirRepository.save(curtir);
        }else{
            throw new RuntimeException("Você já curtiu essa proposta!");
        }
    }
    public ResponseEntity<Curtir> delete(Long id){
        try {
            curtirRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }

    public int contarPorProposta(Long id){
            var proposta = propostaRepository.getReferenceById(id);
        return curtirRepository.countByProposta(proposta);
    }

    public boolean usuarioCurtiu(Usuario usuario, Proposta proposta){
        return curtirRepository.existsLikeByPropostaAndUsuario(proposta,usuario);
    }
}
