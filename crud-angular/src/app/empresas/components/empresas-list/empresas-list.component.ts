import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Empresa } from '../../model/empresa';

@Component({
  selector: 'app-empresas-list',
  standalone: false,
  templateUrl: './empresas-list.component.html',
  styleUrls: ['./empresas-list.component.scss']
})
export class EmpresasListComponent implements OnInit {

  @Input() empresas: Empresa[] = [];
  @Output() add = new EventEmitter<boolean>();
  @Output() edit = new EventEmitter<Empresa>();
  @Output() delete = new EventEmitter<Empresa>();

  displayedColumns : string[] = ['cnpj', 'nomeFantasia', 'cep', 'actions'];
  constructor(
    ) {

  }

  ngOnInit(): void {}

  onAdd(){
    this.add.emit(true);
  }

  onEdit(empresa: Empresa){
    this.edit.emit(empresa);
  }
  onDelete(empresa: Empresa){
    this.delete.emit(empresa);
  }
}
