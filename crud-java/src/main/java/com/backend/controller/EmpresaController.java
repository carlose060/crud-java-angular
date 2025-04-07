package com.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.EmpresaService;
import com.backend.model.Empresa;
import lombok.AllArgsConstructor;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/empresas") 
@AllArgsConstructor
public class EmpresaController {

    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Empresa> findByCpnj(@PathVariable String cnpj) {
        return empresaService.findByCpnj(cnpj);
    }
    

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Empresa empresa) {
        System.out.println("Fornecedores recebidos: " + empresa.getFornecedores());

        return empresaService.create(empresa);
     
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Empresa> update(@PathVariable String cnpj, @RequestBody Empresa empresa){
        return empresaService.update(cnpj, empresa);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<?> delete(@PathVariable String cnpj) {
        return empresaService.delete(cnpj);
    }
}
