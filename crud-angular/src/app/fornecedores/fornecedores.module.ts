import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { FornecedoresRoutingModule } from './fornecedores-routing.module';
import { FornecedoresComponent } from './conteiners/fornecedores/fornecedores.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { FornecedoresListComponent } from './components/fornecedores-list/fornecedores-list.component';
import { FornecedorFormComponent } from './components/fornecedor-form/fornecedor-form.component';
import { MatRadioModule } from '@angular/material/radio';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';


@NgModule({
  declarations: [
    FornecedoresComponent,
    FornecedoresListComponent,
    FornecedorFormComponent
  ],
  imports: [
    CommonModule,
    FornecedoresRoutingModule,
    MatToolbarModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatRadioModule,
    NgxMaskDirective,
    NgxMaskPipe
  ],
  providers: [
    provideNgxMask()
  ],
})
export class FornecedoresModule { }
