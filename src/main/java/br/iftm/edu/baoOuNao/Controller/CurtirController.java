package br.iftm.edu.baoOuNao.Controller;

import br.iftm.edu.baoOuNao.Repository.CurtirRepository;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import br.iftm.edu.baoOuNao.Service.CurtirLikeService;
import br.iftm.edu.baoOuNao.api.dto.like.ConsultaCurtirDTO;
import br.iftm.edu.baoOuNao.api.mapper.CurtirMapper;
import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/curtir")
public class CurtirController {

    @Autowired
    CurtirLikeService curtirLikeService;
    @Autowired
    CurtirRepository curtirRepository;
    @Autowired
    CurtirMapper curtirMapper;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PropostaRepository propostaRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void curtir(@RequestBody Curtir curtir){
        curtirLikeService.salvar(curtir);
    }

    @GetMapping("/{buscarId}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultaCurtirDTO buscar(@PathVariable Long buscarId){

        return curtirMapper.toConsultaLikeDTO(curtirRepository.getReferenceById(buscarId));
    }

    @GetMapping("/contar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int contarLikesProposta(@PathVariable Long id){
        return curtirLikeService.contarPorProposta(id);
    }

    @DeleteMapping("/{idUsuario}/{idProposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity descurtir(@PathVariable Long idUsuario, @PathVariable Long idProposta){
        var idCurtir = curtirRepository.getReferenceByUsuarioIdAndPropostaId(idUsuario,idProposta);
        curtirRepository.deleteById(idCurtir.getId());
            return ResponseEntity.noContent().build();

    }

    @GetMapping("/existe/{usuarioId}/{propostaId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean usuarioCurtiu(@PathVariable Long usuarioId, @PathVariable Long propostaId ){
        var proposta = propostaRepository.getReferenceById(propostaId);
        var usuario = usuarioRepository.getReferenceById(usuarioId);
        return curtirLikeService.usuarioCurtiu(usuario,proposta);
    }
}