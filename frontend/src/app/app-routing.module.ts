import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { GerenteCreateComponent } from './components/gerente/gerente-create/gerente-create.component';
import { GerenteDeleteComponent } from './components/gerente/gerente-delete/gerente-delete.component';
import { GerenteListComponent } from './components/gerente/gerente-list/gerente-list.component';
import { GerenteUpdateComponent } from './components/gerente/gerente-update/gerente-update.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { OperadorCreateComponent } from './components/operador/operador-create/operador-create.component';
import { OperadorDeleteComponent } from './components/operador/operador-delete/operador-delete.component';
import { OperadorListComponent } from './components/operador/operador-list/operador-list.component';
import { OperadorUpdateComponent } from './components/operador/operador-update/operador-update.component';
import { ProdutoCreateComponent } from './components/produto/produto-create/produto-create.component';
import { ProdutoDeleteComponent } from './components/produto/produto-delete/produto-delete.component';
import { ProdutoListComponent } from './components/produto/produto-list/produto-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  {
    path: '', component: NavComponent, canActivate: [AuthGuard], children: [
      {path: 'home',                         component: HomeComponent},

      {path: 'operador',              component: OperadorListComponent},
      {path: 'operador/create',     component: OperadorCreateComponent},
      {path: 'operador/update/:id', component: OperadorUpdateComponent},
      {path: 'operador/delete/:id', component: OperadorDeleteComponent},

      {path: 'gerente',              component: GerenteListComponent},
      {path: 'gerente/create',     component: GerenteCreateComponent},
      {path: 'gerente/update/:id', component: GerenteUpdateComponent},
      {path: 'gerente/delete/:id', component: GerenteDeleteComponent},

      {path: 'produto',              component: ProdutoListComponent},
      {path: 'produto/create',     component: ProdutoCreateComponent},
      {path: 'gerente/delete/:id', component: ProdutoDeleteComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
