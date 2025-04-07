import { config } from './../../app.config.server';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Empresa } from '../model/empresa';

@Injectable({
  providedIn: 'root'
})
export class EmpresasService {

  constructor(private httpClient: HttpClient) {  }

  getEmpresas() {
    return this.httpClient.get<Empresa[]>('api/empresas')
  }



  getEmpresa(cnpj: string) {
    return this.httpClient.get<Empresa>(`api/empresas/${cnpj}`)
  }

  save(empresa: Empresa) {
    return this.httpClient.post<Empresa>('api/empresas', empresa)
  }

  update(empresa: Empresa) {
    return this.httpClient.put<Empresa>(`api/empresas/${empresa.cnpj}`, empresa)
  }

  remove(cnpj: string) {
    return this.httpClient.delete<Empresa>(`api/empresas/${cnpj}`)
  }

}
