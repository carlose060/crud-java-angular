import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { EmpresasService } from '../../servicos/empresas.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-empresa-form',
  standalone: false,
  templateUrl: './empresa-form.component.html',
  styleUrl: './empresa-form.component.scss'
})

export class EmpresaFormComponent implements OnInit {

  form: FormGroup;
  editando = false;

  constructor(
    private formBuild: FormBuilder,
    private service: EmpresasService,
    private snackbar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router,
    ){
      this.form = this.formBuild.group({
        cnpj:  ['', [Validators.required, Validators.minLength(14), Validators.maxLength(14)]],
        nomeFantasia: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
        cep: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
      });
    }
  ngOnInit(): void {
      const cnpj = this.route.snapshot.paramMap.get('cnpj');
      if (cnpj) {
        this.editando = true;

        this.service.getEmpresa(cnpj).subscribe((empresa) => {
          this.form.patchValue(empresa);
          this.form.get('cnpj')?.disable();
        });
      }
  }

  onSubmit(){
    // Habilita o campo cnpj para que ele possa ser enviado na requisição
    this.form.get('cnpj')?.enable();
    const request = this.editando ? this.service.update(this.form.value) : this.service.save(this.form.value);

    request.subscribe({
      next: (response) => {
        this.snackbar.open('Sucesso ao salvar empresa', '', {duration: 3000});
        this.router.navigate(['empresas/']);
      },error: (error) => {
        this.snackbar.open('Erro ao salvar empresa', '', {duration: 3000});
      }
   })};

   onBack(){
    this.router.navigate(['empresas/']);
   }
}
