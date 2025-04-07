import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FornecedoresComponent } from './conteiners/fornecedores/fornecedores.component';
import { FornecedorFormComponent } from './components/fornecedor-form/fornecedor-form.component';

const routes: Routes = [
  {path: '', component: FornecedoresComponent},
  {path: 'new', component: FornecedorFormComponent},
  {path: 'edit/:cpfCnpj', component: FornecedorFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FornecedoresRoutingModule { }
