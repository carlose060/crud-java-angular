import { Component, OnInit } from '@angular/core';
import { Fornecedor } from '../../model/fornecedor';
import { FornecedoresService } from '../../servicos/fornecedores.service';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fornecedores',
  standalone: false,
  templateUrl: './fornecedores.component.html',
  styleUrl: './fornecedores.component.scss'
})
export class FornecedoresComponent implements OnInit {


  fornecedores$: Observable<Fornecedor[]> | null = null;
  displayedColumns : string[] = ['cpfCnpj', 'nome', 'cep', 'actions'];

  constructor(
    private fornecedoresService: FornecedoresService,
    private snackbar: MatSnackBar,
    private router: Router,
  ) {
    this.fornecedores$ =  this.fornecedoresService.getFornecedores();
  }

  ngOnInit(): void {
  }
  onAdd(){
    this.router.navigate(['fornecedores/new']);
  }
  onEdit(fornecedor: Fornecedor){
    this.router.navigate(['fornecedores/edit', fornecedor.cpfCnpj]);
  }
  onDelete(fornecedor: Fornecedor){
    console.log(fornecedor);
    this.fornecedoresService.remove(fornecedor.cpfCnpj).subscribe({
      next: () => {
        this.snackbar.open('Sucesso ao deletar fornecedor', '', {duration: 3000});
        this.fornecedores$ = this.fornecedoresService.getFornecedores();
      },
      error: () => {
        this.snackbar.open('Falha ao deletar fornecedor', '', {duration: 3000});
      }
    });
  }


}
