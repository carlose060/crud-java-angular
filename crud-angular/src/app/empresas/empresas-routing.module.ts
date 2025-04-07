import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpresasComponent } from './conteiners/empresas/empresas.component';
import { EmpresaFormComponent } from './components/empresa-form/empresa-form.component';

const routes: Routes = [
  { path: '', component: EmpresasComponent },
  { path: 'new', component: EmpresaFormComponent },
  { path: 'edit/:cnpj', component: EmpresaFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpresasRoutingModule { }
