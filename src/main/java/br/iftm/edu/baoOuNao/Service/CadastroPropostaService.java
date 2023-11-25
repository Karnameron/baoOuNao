package br.iftm.edu.baoOuNao.Service;

import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeNaoEncontradaException;
import br.iftm.edu.baoOuNao.Repository.UsuarioRepository;
import br.iftm.edu.baoOuNao.api.dto.proposta.PropostaCadastroDto;
import br.iftm.edu.baoOuNao.api.mapper.PropostaMapper;
import br.iftm.edu.baoOuNao.domain.model.proposta.Categoria;
import br.iftm.edu.baoOuNao.domain.model.proposta.Proposta;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CadastroPropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropostaMapper propostaMapper;

    public Proposta salvar(Proposta proposta){
        var usuario = proposta.getUsuario();
        var categoria = proposta.getCategoria();
        var contador = propostaRepository.countPropostaByUsuarioAndCategoria(usuario,categoria);
        System.out.println(contador);
        if(contador >= 3){
            throw new RuntimeException("Você passou do limite de três propostas por categoria!");
        }else{
            return propostaRepository.save(proposta);
        }
    }

    public void deletar(Long usuarioId){
        try{
            propostaRepository.deleteById(usuarioId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de proposta com o código %d", usuarioId));
        }
    }

    public ResponseEntity<?> atualizarParcial(Long propostaId, Map<String, Object> campos){
        Optional<Proposta> propostaAtual = propostaRepository.findById(propostaId);
        if (propostaAtual.isPresent()) {
            merge(campos, propostaAtual.get());
            Proposta propostaSalva = salvar(propostaAtual.get());
            return ResponseEntity.ok(propostaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Proposta> moderar(Long propostaId, Map<String,Object> campos){
        Optional<Proposta> propostaAtual = propostaRepository.findById(propostaId);
        if(propostaAtual.isPresent()){
            merge(campos,propostaAtual.get());
            Proposta propostaSalva = salvar(propostaAtual.get());
            return ResponseEntity.ok(propostaSalva);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Proposta> atualizar(Long propostaId, PropostaCadastroDto proposta){
        Optional<Proposta> propostaAtual = propostaRepository.findById(propostaId);
        if(propostaAtual.isPresent()){
            BeanUtils.copyProperties(propostaMapper.toEntity(proposta), propostaAtual.get(),"id");
            Proposta propostaSalva = salvar(propostaAtual.get());
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
    public int contarLikes(Long idProposta){
        var proposta = propostaRepository.getReferenceById(idProposta);
        return proposta.getQtdlikes();
    }

    public List<Proposta> buscarTodasPropostasPorUser(Long usuarioId){
        var usuario = usuarioRepository.getReferenceById(usuarioId);
        return propostaRepository.findAllByUsuario(usuario);
    }
}