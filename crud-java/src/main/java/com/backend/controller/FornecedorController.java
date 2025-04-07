package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.FornecedorService;
import com.backend.model.Fornecedor;


@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> getAllFornecedores() {
        return fornecedorService.getAllFornecedores();
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Fornecedor> findByCpnjCpf(@PathVariable String cnpj) {
        return fornecedorService.findByCpnjCpf(cnpj);
    }
    

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.create(fornecedor);
     
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Fornecedor> update(@PathVariable String cnpj, @RequestBody Fornecedor fornecedor){
        return fornecedorService.update(cnpj, fornecedor);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<?> delete(@PathVariable String cnpj) {
        return fornecedorService.delete(cnpj);
    }
}
