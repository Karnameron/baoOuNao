package br.iftm.edu.baoOuNao.Controller;

import br.iftm.edu.baoOuNao.Repository.CurtirRepository;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import br.iftm.edu.baoOuNao.Service.CurtirLikeService;
import br.iftm.edu.baoOuNao.api.dto.like.ConsultaCurtirDTO;
import br.iftm.edu.baoOuNao.api.mapper.CurtirMapper;
import br.iftm.edu.baoOuNao.domain.model.curtir.Curtir;
import org.springframework.beans.factory.annotation.Autowired;
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
    PropostaRepository propostaRepository;
    @PostMapping
    public void curtir(@RequestBody Curtir curtir){
        curtirLikeService.salvar(curtir);
    }

    @GetMapping("/{buscarId}")
    public ConsultaCurtirDTO buscar(@PathVariable Long buscarId){

        return curtirMapper.toConsultaLikeDTO(curtirRepository.getReferenceById(buscarId));
    }

    @GetMapping("/contar/{id}")
    public int contarLikesProposta(@PathVariable Long id){
        return curtirLikeService.contarPorProposta(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity descurtir(@PathVariable Long id){
        try{
            curtirRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        }catch (Exception e){
            throw new RuntimeException("Erro ao descurtir a proposta!");
        }
    }


}
