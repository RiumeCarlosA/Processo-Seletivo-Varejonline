import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Operador } from 'src/app/models/operador';
import { OperadorService } from 'src/app/services/operador.service';

@Component({
  selector: 'app-operador-create',
  templateUrl: './operador-create.component.html',
  styleUrls: ['./operador-create.component.css']
})
export class OperadorCreateComponent {

  operador: Operador = {
    id:         '',
    nome:       '',
    usuario:    '',
    senha:      '',
    perfis:     [],
    dataCriacao: ''
  }

  nome: FormControl = new FormControl(null, Validators.minLength(3));
  usuario: FormControl = new FormControl(null, Validators.minLength(3));
  senha: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(
    private service: OperadorService,
    private toast:    ToastrService,
    private router:          Router,
    ) { }

  ngOnInit(): void { }

  create(): void {
    this.service.create(this.operador).subscribe(() => {
      this.toast.success('Operador cadastrado com sucesso', 'Cadastro');
      this.router.navigate(['operador'])
    }, ex => {
      if(ex.error.errors) {
        ex.error.errors.forEach(element => {
          this.toast.error(element.message);
        });
      } else {
        this.toast.error(ex.error.message);
      }
    })
  }

  validaCampos(): boolean {
    return this.nome.valid && this.usuario.valid && this.senha.valid
  }
}
