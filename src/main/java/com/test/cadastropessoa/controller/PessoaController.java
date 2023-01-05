package com.test.cadastropessoa.controller;

import com.test.cadastropessoa.model.Endereco;
import com.test.cadastropessoa.model.Pessoa;
import com.test.cadastropessoa.service.PessoaService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @RequestMapping(value = "/cadastrarPessoa", method = RequestMethod.POST)
    public ResponseEntity<Pessoa> cadastrarPessoa(@Validated @RequestBody Pessoa pessoa, HttpServletResponse httpServletResponse) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/pessoas/cadastrarPessoa").toUriString());
        return ResponseEntity.created(uri).body(pessoaService.cadastrarPessoa(pessoa));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> buscarPessoa(@RequestBody @PathVariable Long id) {
        return ResponseEntity.ok().body(pessoaService.buscarPessoa(id));
    }

    @RequestMapping(value = "/listarPessoas", method = RequestMethod.GET)
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.ok().body(pessoaService.listarPessoas());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> atualizarPessoa(@Validated @PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(pessoaService.atualizarPessoa(id));
    }

    @RequestMapping(value = "/listarEnderecos", method = RequestMethod.GET)
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        return ResponseEntity.ok().body(pessoaService.listarEnderecos());
    }
}
