import { Routes } from '@angular/router';

export const routes: Routes = [
  {path : '', redirectTo: 'fornecedores', pathMatch: 'full'},
  {path : 'empresas', loadChildren: () => import('./empresas/empresas.module').then(m => m.EmpresasModule)},
  {path : 'fornecedores', loadChildren: () => import('./fornecedores/fornecedores.module').then(m => m.FornecedoresModule)},
];
