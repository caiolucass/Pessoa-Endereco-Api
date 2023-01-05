package com.test.cadastropessoa.integration;

import com.test.cadastropessoa.CadastroPessoaApplication;
import com.test.cadastropessoa.model.Pessoa;
import com.test.cadastropessoa.repository.PessoaRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CadastroPessoaApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {

    @MockBean
    private PessoaRepository pessoaRepository;
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    private void instanciarNovaPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Caio Lucas");
        pessoa.setCpf("14484348667");
        pessoa.setDataDeNascimento(LocalDate.of(2000, 10, 20));
        pessoa.setEnderecos(new ArrayList<>());
        ResponseEntity<Pessoa> responseEntity = restTemplate
                .postForEntity((createURLWithPort("/pessoas")), pessoa, Pessoa.class);
    }

    @Test
    public void listarPessoasTest() throws JSONException {
        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/listarPessoas"),
                HttpMethod.GET, httpEntity, String.class);
        assertEquals(200, responseEntity.getStatusCode().value());
        JSONAssert.assertEquals(null, responseEntity.getBody(), true);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    public void deletarPessoaTest() throws JSONException {
        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/deletarPessoas/1"),
                HttpMethod.DELETE, httpEntity, String.class);
        assertEquals(204, responseEntity.getStatusCode().value());
//        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
        verify(pessoaRepository, times(1)).delete(any(Pessoa.class));
    }

    @Test
    public void listarEnderecoTest() throws JSONException {
        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/listarEndereco"),
                HttpMethod.GET, httpEntity, String.class);
        assertEquals(200, responseEntity.getStatusCode().value());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    public void atualizarPessoaTest() throws JSONException {
        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/atualizarPessoa"),
                HttpMethod.PUT, httpEntity, String.class);
        assertEquals(200, responseEntity.getStatusCode().value());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }
}