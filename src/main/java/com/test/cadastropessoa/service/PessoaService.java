package com.test.cadastropessoa.service;

import com.test.cadastropessoa.model.Endereco;
import com.test.cadastropessoa.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Pessoa cadastrarPessoa(Pessoa pessoa);

    Pessoa buscarPessoa(Long id);

    List<Pessoa> listarPessoas();

   void deletarPessoa(Long id);

    Pessoa atualizarPessoa(Long id);

    List<Endereco> listarEnderecos();

}