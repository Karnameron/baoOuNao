package br.iftm.edu.baoOuNao.Controller;

import br.iftm.edu.baoOuNao.Exception.Usuario.UsuarioNaoEncontradoException;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaCadastroDto;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaConsultaDto;
import br.iftm.edu.baoOuNao.api.mapper.PropostaMapper;
import br.iftm.edu.baoOuNao.domain.model.Proposta;
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
    public void adicionar(@RequestBody @Valid PropostaCadastroDto proposta){
        var entityProposta = propostaMapper.toEntity(proposta);
        cadastroPropostaService.salvar(entityProposta);
    }

    @DeleteMapping("/{propostaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long propostaId){
        cadastroPropostaService.deletar(propostaId);
    }

    @PutMapping("/{propostaId}")
    public ResponseEntity<Proposta> atualizar(@PathVariable Long propostaId, @RequestBody @Valid PropostaCadastroDto usuario){
        Optional<Proposta> propostaAtual = propostaRepository.findById(propostaId);
        if(propostaAtual.isPresent()){
            BeanUtils.copyProperties(propostaMapper.toEntity(usuario), propostaAtual.get(),"id");
            Proposta propostaSalva = cadastroPropostaService.salvar(propostaAtual.get());
            return ResponseEntity.ok(propostaSalva);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{propostaId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long propostaId, @RequestBody Map<String, Object> campos) {
        Optional<Proposta> propostaAtual = propostaRepository.findById(propostaId);
        if (propostaAtual.isPresent()) {
            merge(campos, propostaAtual.get());
            Proposta propostaSalva = cadastroPropostaService.salvar(propostaAtual.get());
            return ResponseEntity.ok(propostaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Proposta propostaDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Proposta propostaOrigem = objectMapper.convertValue(dadosOrigem, Proposta.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)-> {
            Field field = ReflectionUtils.findField(Proposta.class, nomePropriedade);

            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, propostaOrigem);
            ReflectionUtils.setField(field, propostaDestino, novoValor);
        });
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





}
