package br.iftm.edu.baoOuNao.Service;

import br.iftm.edu.baoOuNao.Exception.Geral.EntidadeNaoEncontradaException;
import br.iftm.edu.baoOuNao.domain.model.Proposta;
import br.iftm.edu.baoOuNao.Repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroPropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    public Proposta salvar(Proposta proposta){
        return propostaRepository.save(proposta);
    }

    public void deletar(Long usuarioId){
        try{
            propostaRepository.deleteById(usuarioId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de proposta com o código %d", usuarioId));
        }
    }

}
