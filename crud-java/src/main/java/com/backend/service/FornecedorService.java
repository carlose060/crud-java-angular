package com.backend.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.backend.model.Empresa;
import com.backend.model.Fornecedor;
import com.backend.repository.EmpresaRepository;
import com.backend.repository.FornecedorRepository;


@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }
    
    public ResponseEntity<Fornecedor> findByCpnjCpf(String cnpjCpf) {
        return fornecedorRepository.findById(cnpjCpf)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }  

    public ResponseEntity<?> create(Fornecedor fornecedor) {
    Set<Empresa> empresasAssociadas = new HashSet<>();

    if (fornecedor.getEmpresas() != null) {
        for (Empresa empresa : fornecedor.getEmpresas()) {
            empresaRepository.findById(empresa.getCnpj()).ifPresent(empresasAssociadas::add);
        }
    }

    fornecedor.setEmpresas(empresasAssociadas);

    return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorRepository.save(fornecedor));
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
    
 
    public ResponseEntity<?> delete(String cpfCnpj) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(cpfCnpj);
    
        if (fornecedorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Fornecedor fornecedor = fornecedorOptional.get();
    
        // Remove a associação nas empresas antes de deletar
        fornecedor.getEmpresas().forEach(empresa -> empresa.getFornecedores().remove(fornecedor));
        fornecedor.getEmpresas().clear();
    
        fornecedorRepository.delete(fornecedor);
    
        return ResponseEntity.ok().build();
    }
    
    
}
