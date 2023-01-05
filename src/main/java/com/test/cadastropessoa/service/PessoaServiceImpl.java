package com.test.cadastropessoa.service;

import com.test.cadastropessoa.model.Endereco;
import com.test.cadastropessoa.model.Pessoa;
import com.test.cadastropessoa.repository.EnderecoRepository;
import com.test.cadastropessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    @Override
    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        log.info("Salvando nova pessoa {} no banco de dados.", pessoa.getNome());
        Optional<Pessoa> pessoaOptional = pessoaRepository.findByNome(pessoa.getNome());

        //valida se o cpf da pessoa ja está cadastrada
        if(pessoaOptional.isPresent()){
            throw new IllegalStateException("CPF ja cadastrado!");
        }
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa buscarPessoa(Long id) {
        log.info("Fetching pessoa: {}", id);
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            return pessoa.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Pessoa atualizarPessoa(Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(pessoa.getNome());
                    pessoa.setCpf(pessoa.getCpf());
                    pessoa.setDataDeNascimento(pessoa.getDataDeNascimento());
                    pessoa.setEnderecos(pessoa.getEnderecos());
                    return pessoaRepository.save(pessoa);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Pessoa> listarPessoas() {
        log.info("Fetching todas as pessoas.");
        return pessoaRepository.findAll();
    }

    @Override
    public void deletarPessoa(Long id) {
        log.info("Fetching pessoa: {}", id);
        boolean exists = pessoaRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        pessoaRepository.deleteById(id);
    }

    @Override
    public List<Endereco> listarEnderecos() {
        log.info("Fetching todas os enderecos.");
        return enderecoRepository.findAll();
    }
}
