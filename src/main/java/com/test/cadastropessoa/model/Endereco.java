package com.test.cadastropessoa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Table(name = "endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Favor preencher campo endereco.")
    private String logradouro;

    @NotBlank(message = "Favor preencher campo cep.")
    private String cep;

    @NotNull(message = "Favor preencher campo numero.")
    private Integer numero;

    @NotBlank(message = "Favor preencher campo cidade.")
    private String cidade;

    @ManyToOne
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return id != null && Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
