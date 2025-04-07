import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
  {path : '', redirectTo: 'fornecedores', pathMatch: 'full'},
  {path : 'empresas', loadChildren: () => import('./empresas/empresas.module').then(m => m.EmpresasModule)},
  {path : 'fornecedores', loadChildren: () => import('./fornecedores/fornecedores.module').then(m => m.FornecedoresModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
