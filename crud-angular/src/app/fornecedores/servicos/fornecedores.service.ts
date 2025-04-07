import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Fornecedor } from '../model/fornecedor';

@Injectable({
  providedIn: 'root'
})
export class FornecedoresService {

  constructor(private httpClient: HttpClient) { }

  getFornecedores() {
    return this.httpClient.get<Fornecedor[]>('api/fornecedores')
  }

  getFornecedor(cpfCnpj: string) {
    return this.httpClient.get<Fornecedor>(`api/fornecedores/${cpfCnpj}`);
  }

  remove(cpfCnpj: string) {

    //FAZER A FUNÇão
    return this.httpClient.delete(`api/fornecedores/${cpfCnpj}`);
  }

  update(fornecedor: Fornecedor) {
    return this.httpClient.put(`api/fornecedores/${fornecedor.cpfCnpj}`, fornecedor);
  }

  save(fornecedor: Fornecedor) {
    return this.httpClient.post('api/fornecedores', fornecedor);
  }
}
