import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// Para trabalhar com formulários no Angular 12
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
// Para realizar requisições HTTP
import { HttpClientModule } from '@angular/common/http';
// Imports para componentes do Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
// Componentes do projeto
import { LoginComponent } from './components/login/login.component';
import { ToastrModule } from 'ngx-toastr';
import { AuthInterceptorProvider } from './interceptor/auth.interceptor';
import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';
import { OperadorListComponent } from './components/operador/operador-list/operador-list.component';
import { OperadorCreateComponent } from './components/operador/operador-create/operador-create.component';
import { OperadorDeleteComponent } from './components/operador/operador-delete/operador-delete.component';
import { OperadorUpdateComponent } from './components/operador/operador-update/operador-update.component';
import { AuthGuard } from './auth/auth.guard';
import { GerenteUpdateComponent } from './components/gerente/gerente-update/gerente-update.component';
import { GerenteListComponent } from './components/gerente/gerente-list/gerente-list.component';
import { GerenteDeleteComponent } from './components/gerente/gerente-delete/gerente-delete.component';
import { GerenteCreateComponent } from './components/gerente/gerente-create/gerente-create.component';
import { ProdutoListComponent } from './components/produto/produto-list/produto-list.component';
import { ProdutoCreateComponent } from './components/produto/produto-create/produto-create.component';
import { ProdutoReadComponent } from './components/produto/produto-read/produto-read.component';
import { ProdutoDeleteComponent } from './components/produto/produto-delete/produto-delete.component';
import { EstoqueListComponent } from './components/estoque/estoque-list/estoque-list.component';
import { MovimentacaoCreateComponent } from './components/estoque/movimentacao-create/movimentacao-create.component';
//import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavComponent,
    HomeComponent,
    OperadorListComponent,
    OperadorCreateComponent,
    OperadorDeleteComponent,
    OperadorUpdateComponent,
    GerenteUpdateComponent,
    GerenteListComponent,
    GerenteDeleteComponent,
    GerenteCreateComponent,
    ProdutoListComponent,
    ProdutoCreateComponent,
    ProdutoReadComponent,
    ProdutoDeleteComponent,
    EstoqueListComponent,
    MovimentacaoCreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    // Forms
    FormsModule,
    ReactiveFormsModule,
    // Requisições http
    HttpClientModule,
    // Angular Material
    MatFormFieldModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatSelectModule,
    MatInputModule,
    MatRadioModule,
    MatTableModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      progressBar: true
    }),
  ],
  providers: [AuthInterceptorProvider, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
