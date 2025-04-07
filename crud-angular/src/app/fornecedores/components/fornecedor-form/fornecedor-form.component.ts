import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FornecedoresService } from '../../servicos/fornecedores.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Empresa } from '../../../empresas/model/empresa';
import { EmpresasService } from '../../../empresas/servicos/empresas.service';

@Component({
  selector: 'app-fornecedor-form',
  standalone: false,
  templateUrl: './fornecedor-form.component.html',
  styleUrl: './fornecedor-form.component.scss'
})
export class FornecedorFormComponent {
  form: FormGroup;
  editando = false;

  empresas: Empresa[] = [];

  constructor(
    private formBuild: FormBuilder,
    private service: FornecedoresService,
    private snackbar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router,
    private empresasService: EmpresasService
    ){
      this.form = this.formBuild.group({
        tipoPessoa: ['1'],
        cpfCnpj:  ['', [Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
        nome: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
        cep: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
        email: ['', [Validators.required, Validators.email]],
        dataNascimento: [''],
        rg: [''],
        empresas: [[]]
      });

    }
  ngOnInit(): void {

      const cpfCnpj = this.route.snapshot.paramMap.get('cpfCnpj');
      if (cpfCnpj) {
        this.editando = true;

        this.service.getFornecedor(cpfCnpj).subscribe((fornecedor) => {
          this.form.patchValue(fornecedor);
          this.form.get('cpfCnpj')?.disable();
        });
      }
      this.empresasService.getEmpresas().subscribe({
        next: (res) => this.empresas = res,
        error: (err) => console.error('Erro ao carregar empresas:', err)
      });
  }

  onSubmit(){
    // Habilita o campo cnpj para que ele possa ser enviado na requisição
    this.form.get('cpfCnpj')?.enable();

    const request = this.editando ? this.service.update(this.form.value) : this.service.save(this.form.value);

    request.subscribe({
      next: (response) => {
        this.snackbar.open('Sucesso ao salvar fornecedor', '', {duration: 3000});
        this.router.navigate(['fornecedores/']);
      },error: (error) => {
        this.snackbar.open('Erro ao salvar fornecedor', '', {duration: 3000});
      }
   })};

   onBack(){
    this.router.navigate(['fornecedores/']);
   }
}
