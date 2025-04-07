package com.backend.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Fornecedor {
    
    @Id
    @Column(nullable = false, length = 14)
    private String cpfCnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(length = 10, nullable = true)
    private String rg;

    @Column(name="data_nascimento", length = 10, nullable = true)
    private String dataNascimento;

    @ManyToMany(mappedBy = "fornecedores")
    private Set<Empresa> empresas = new HashSet<>();


    public boolean isPessoaFisica() {
        return cpfCnpj != null && cpfCnpj.length() <= 11;
    }
}

