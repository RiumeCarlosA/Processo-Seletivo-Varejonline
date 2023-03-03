import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimentacao } from 'src/app/models/movimentacao';
import { MovimentacaoService } from 'src/app/services/movimentacao.service';

@Component({
  selector: 'app-movimentacao-create',
  templateUrl: './movimentacao-create.component.html',
  styleUrls: ['./movimentacao-create.component.css']
})
export class MovimentacaoCreateComponent {

  movimentacao: Movimentacao = {
    quantidade:      '',
    motivo:          '',
    nomePessoa:      '',
    nomeProduto:     '',
    movimentos:      ''
  }

  quantidade: FormControl = new FormControl(null, [Validators.required]);
  motivo: FormControl = new FormControl(null, [Validators.required]);
  nomePessoa: FormControl = new FormControl(null, [Validators.required]);
  nomeProduto: FormControl = new FormControl(null, [Validators.required]);
  movimentos: FormControl = new FormControl(null, [Validators.required]);

  constructor(
    private service: MovimentacaoService,
    private toast:    ToastrService,
    private router:          Router,
    ) { }

  ngOnInit(): void { }

  create(): void {
    this.service.create(this.movimentacao).subscribe(() => {
      this.toast.success('Movimentação cadastrada com sucesso', 'Cadastro');
      this.router.navigate(['estoque'])
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
    return this.quantidade.valid && this.motivo.valid && this.nomePessoa.valid && this.nomeProduto.valid && this.movimentos.valid 
  }
}
