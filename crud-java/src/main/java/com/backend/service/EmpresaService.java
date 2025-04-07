package com.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.model.Empresa;
import com.backend.model.Fornecedor;
import com.backend.repository.EmpresaRepository;
import com.backend.repository.FornecedorRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
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

        // Garante que todos os fornecedores existem no banco
        Set<Fornecedor> fornecedores = new HashSet<>();
        for (Fornecedor f : empresa.getFornecedores()) {
            fornecedorRepository.findById(f.getCpfCnpj()).ifPresent(fornecedores::add);
        }
        empresa.setFornecedores(fornecedores);

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
        Optional<Empresa> empresaOptional = empresaRepository.findById(cnpj);
    
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Empresa empresa = empresaOptional.get();
    
        // Remove os vínculos com fornecedores antes de deletar
        empresa.getFornecedores().forEach(fornecedor -> fornecedor.getEmpresas().remove(empresa));
        empresa.getFornecedores().clear();
    
        empresaRepository.delete(empresa);
    
        return ResponseEntity.ok().build();
    }
    
}

