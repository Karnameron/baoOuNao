package br.iftm.edu.baoOuNao.Controller;

import br.iftm.edu.baoOuNao.Exception.Usuario.UsuarioNaoEncontradoException;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaCadastroDto;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaConsultaDto;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaFeedbackDto;
import br.iftm.edu.baoOuNao.api.mapper.PropostaMapper;
import br.iftm.edu.baoOuNao.domain.model.proposta.Categoria;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import br.iftm.edu.baoOuNao.Service.CadastroPropostaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CadastroPropostaService cadastroPropostaService;

    @Autowired
    private PropostaMapper propostaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid Proposta proposta){
        cadastroPropostaService.salvar(proposta);
    }

    @DeleteMapping("/{propostaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long propostaId){
        cadastroPropostaService.deletar(propostaId);
    }

    @PutMapping("/{propostaId}")
    public ResponseEntity<Proposta> atualizar(@PathVariable Long propostaId, @RequestBody @Valid PropostaCadastroDto proposta){
        return cadastroPropostaService.atualizar(propostaId,proposta);
    }
    @PatchMapping("/{propostaId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long propostaId, @RequestBody Map<String, Object> campos) {
        return cadastroPropostaService.atualizarParcial(propostaId,campos);
    }


    @GetMapping
    public List<PropostaConsultaDto> buscar(){
        var propostas = propostaMapper.toCollectionModel(propostaRepository.findAll());
        return propostas;
    }

    @GetMapping("/{propostaId}")
    public PropostaConsultaDto buscarPorId(@PathVariable Long propostaId){
        var proposta = propostaRepository.findById(propostaId);
        if(proposta.isPresent()){
            return propostaMapper.toModelConsulta(propostaRepository.getReferenceById(propostaId));
        }else {
            throw new UsuarioNaoEncontradoException("Proposta Não encontrada!");
        }
    }

    @PatchMapping("/moderar/{propostaId}")
    public PropostaFeedbackDto moderar(@PathVariable long propostaId, @RequestBody Map<String, Object> campos){
        if(campos.containsKey("situacao") && campos.containsKey("feedback")){
            var campo = cadastroPropostaService.moderar(propostaId,campos).getBody();
            return propostaMapper.toFeedback(campo);
        }else{
            throw new RuntimeException("Campo situação não encontrado!");
        }
    }
@PostMapping("/categoria")
public List<PropostaConsultaDto> buscarPorCategoria(@RequestBody String categoria){
        var categoriaEnum = Enum.valueOf(Categoria.class,categoria);
        return propostaMapper.toCollectionModel(propostaRepository.findAllByCategoria(categoriaEnum));
}
    @GetMapping("/contarlike/{propostaId}")
    public int contarlikes(@PathVariable Long propostaId)
    {
        return cadastroPropostaService.contarLikes(propostaId);
    }




}
