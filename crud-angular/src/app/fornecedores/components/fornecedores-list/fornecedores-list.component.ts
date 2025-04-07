
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Fornecedor } from '../../model/fornecedor'


@Component({
  selector: 'app-fornecedores-list',
  standalone: false,
  templateUrl: './fornecedores-list.component.html',
  styleUrl: './fornecedores-list.component.scss'
})
export class FornecedoresListComponent implements OnInit {

  @Input() fornecedores: Fornecedor[] = [];
  @Output() add = new EventEmitter<boolean>();
  @Output() edit = new EventEmitter<Fornecedor>();
  @Output() delete = new EventEmitter<Fornecedor>();

  displayedColumns : string[] = ['cpfCnpj', 'nome', 'cep', 'dataNascimento', 'rg', 'actions'];
  constructor(
    ) {

  }

  ngOnInit(): void {
  }

  onAdd(){
    this.add.emit(true);
  }
  onEdit(fornecedor: Fornecedor){
    this.edit.emit(fornecedor);
  }
  onDelete(fornecedor: Fornecedor){
    this.delete.emit(fornecedor);
  }
}
