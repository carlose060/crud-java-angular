package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.model.Empresa;
import com.backend.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    
    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

   
    public ResponseEntity<Empresa> findByCpnj(String cnpj) {
        return empresaRepository.findById(cnpj)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> create(Empresa empresa) {
        if (empresaRepository.existsByCnpj(empresa.getCnpj())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já cadastrado");
        }
        if (empresa.getCnpj() != null && empresa.getCnpj().length() > 14) {
            empresa.setCnpj(empresa.getCnpj().substring(0, 14));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaRepository.save(empresa));
     
    }

    
    public ResponseEntity<Empresa> update(String cnpj, @RequestBody Empresa empresa){
        return empresaRepository.findById(cnpj)
        .map(response -> {
            response.setCep(empresa.getCep());
            response.setNomeFantasia(empresa.getNomeFantasia());
            Empresa updated = empresaRepository.save(response);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

  
    public ResponseEntity<?> delete(String cnpj) {
        return empresaRepository.findById(cnpj)
                .map(response -> {
                    empresaRepository.deleteById(cnpj);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}

