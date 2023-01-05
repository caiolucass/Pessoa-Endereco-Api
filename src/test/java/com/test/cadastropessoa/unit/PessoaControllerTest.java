package com.test.cadastropessoa.unit;

import com.test.cadastropessoa.controller.PessoaController;
import com.test.cadastropessoa.model.Pessoa;
import com.test.cadastropessoa.service.PessoaService;
import com.test.cadastropessoa.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @MockBean
    private PessoaService pessoaService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cadastrarPessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Caio");
        pessoa.setDataDeNascimento(LocalDate.of(2000, 10, 20));
        pessoa.setEnderecos(new ArrayList<>());

        given(pessoaService.cadastrarPessoa(pessoa)).willReturn(pessoa);
        when(pessoaService.cadastrarPessoa(new Pessoa())).thenReturn(pessoa);
        mockMvc.perform(post("/pessoas/cadastrarPessoa")
                        .content(JsonUtil.toJson(pessoa))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.nome").value(pessoa.getNome()));
    }
}
