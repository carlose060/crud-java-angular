package com.backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.backend.model.Fornecedor;
import com.backend.repository.FornecedorRepository;


@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;


    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }
    
    public ResponseEntity<Fornecedor> findByCpnjCpf(String cnpjCpf) {
        return fornecedorRepository.findById(cnpjCpf)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }  

    public  ResponseEntity<?> create(Fornecedor fornecedor) {
        if (fornecedor.getCpfCnpj().length() > 14) {
        fornecedor.setCpfCnpj(fornecedor.getCpfCnpj().substring(0, 14));
        }

        if (fornecedor.isPessoaFisica()) {
            if(fornecedor.getRg() == null || fornecedor.getDataNascimento() == null) {
                return ResponseEntity.badRequest().body("Pessoa física deve ter RG e data de nascimento");
            }
        }
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
    public ResponseEntity<Fornecedor> update(String cnpjCpf, @RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.findById(cnpjCpf)
        .map(response -> {
            response.setNome(fornecedor.getNome());
            response.setEmail(fornecedor.getEmail());
            response.setCep(fornecedor.getCep());
            
            if(fornecedor.isPessoaFisica()) {
                response.setRg(fornecedor.getRg()); 
                response.setDataNascimento(fornecedor.getDataNascimento());     
            } 
            response.setDataNascimento(null);
            response.setRg(null);
            Fornecedor updated = fornecedorRepository.save(response);
            return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());  
            
        }
    
    public ResponseEntity<?> delete(String cnpjCpf) {
        return fornecedorRepository.findById(cnpjCpf)
                .map(response -> {
                    fornecedorRepository.deleteById(cnpjCpf);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}
