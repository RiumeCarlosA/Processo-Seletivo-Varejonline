import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { OperadorCreateComponent } from './components/operador/operador-create/operador-create.component';
import { OperadorDeleteComponent } from './components/operador/operador-delete/operador-delete.component';
import { OperadorListComponent } from './components/operador/operador-list/operador-list.component';
import { OperadorUpdateComponent } from './components/operador/operador-update/operador-update.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  {
    path: '', component: NavComponent, canActivate: [AuthGuard], children: [
      {path: 'home',                         component: HomeComponent},

      {path: 'operador',              component: OperadorListComponent},
      {path: 'operador/create',     component: OperadorCreateComponent},
      {path: 'operador/update/:id', component: OperadorUpdateComponent},
      {path: 'operador/delete/:id', component: OperadorDeleteComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
