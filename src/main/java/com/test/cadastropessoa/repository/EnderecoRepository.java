package com.test.cadastropessoa.repository;

import com.test.cadastropessoa.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findEnderecoByCep(String cep);
}
