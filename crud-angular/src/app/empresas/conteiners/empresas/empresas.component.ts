import { Component, OnInit } from '@angular/core';
import { Empresa } from '../../model/empresa';
import { EmpresasService } from '../../servicos/empresas.service';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';



@Component({
  selector: 'app-empresas',
  standalone: false,
  templateUrl: './empresas.component.html',
  styleUrls: ['./empresas.component.scss']

})
export class EmpresasComponent  implements OnInit {

  empresas$: Observable<Empresa[]>;
  displayedColumns : string[] = ['cnpj', 'nomeFantasia', 'cep', 'actions'];

  constructor(
    private empresasService: EmpresasService,
    private snackbar: MatSnackBar,
    private router: Router,
  ) {

    this.empresas$ = this.empresasService.getEmpresas();

  }

  ngOnInit(): void { }

  onAdd(){
    this.router.navigate(['empresas/new']);
  }
  onEdit(empresa: Empresa){
    this.router.navigate(['empresas/edit', empresa.cnpj]);
  }
  onDelete(empresa: Empresa){
    this.empresasService.remove(empresa.cnpj).subscribe({
      next: () => {
        this.snackbar.open('Sucesso ao deletar empresa', '', {duration: 3000});
        this.empresas$ = this.empresasService.getEmpresas();
      },
      error: () => {
        this.snackbar.open('Falha ao deletar empresa', '', {duration: 3000});
      }
    });
  }
}
