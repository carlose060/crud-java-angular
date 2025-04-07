package com.backend.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;





@Entity
public class Empresa {

    @Id
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(length = 8)
    private String cep;

    @ManyToMany
    @JoinTable(name = "empresa_fornecedor",
            joinColumns = @JoinColumn(name = "empresa_cnpj"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_cpfCnpj"))
    private Set<Fornecedor> fornecedores = new HashSet<>();

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(Set<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
        fornecedor.getEmpresas().add(this);
    }
    public void removeFornecedor(Fornecedor fornecedor) {
        this.fornecedores.remove(fornecedor);
        fornecedor.getEmpresas().remove(this);
    }
    

}
