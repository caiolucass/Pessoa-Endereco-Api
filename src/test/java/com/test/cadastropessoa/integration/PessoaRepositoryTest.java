package com.test.cadastropessoa.integration;

import com.test.cadastropessoa.model.Pessoa;
import com.test.cadastropessoa.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository pessoaRepository;

    @Test
    public void verificarExistencia_quandoCriada() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas.size()).isEqualTo(0);
    }
}
