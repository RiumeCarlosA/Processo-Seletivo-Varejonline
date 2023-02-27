import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gerente } from 'src/app/models/gerente';
import { GerenteService } from 'src/app/services/gerente.service';

@Component({
  selector: 'app-gerente-create',
  templateUrl: './gerente-create.component.html',
  styleUrls: ['./gerente-create.component.css']
})
export class GerenteCreateComponent {

  gerente: Gerente = {
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
    private service: GerenteService,
    private toast:    ToastrService,
    private router:          Router,
    ) { }

  ngOnInit(): void { }

  create(): void {
    this.service.create(this.gerente).subscribe(() => {
      this.toast.success('Gerente cadastrado com sucesso', 'Cadastro');
      this.router.navigate(['gerente'])
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
