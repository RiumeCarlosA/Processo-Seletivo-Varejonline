import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Produto } from 'src/app/models/produto';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto-create',
  templateUrl: './produto-create.component.html',
  styleUrls: ['./produto-create.component.css']
})
export class ProdutoCreateComponent {

  produto: Produto = {
    nome:            '',
    codBarra:        '',
    qtdMin:          '',
    saldoInicial:    '',
  }

  nome: FormControl = new FormControl(null, [Validators.required])
  codBarra: FormControl = new FormControl(null, [Validators.required])
  qtdMin: FormControl = new FormControl(null, [Validators.required])
  saldoInicial: FormControl = new FormControl(null, [Validators.required])

  constructor(
    private produtoService: ProdutoService,
    private toastService:    ToastrService,
    private routerService:          Router,
  ) { }

  create(): void {
    this.produtoService.create(this.produto).subscribe(resposta => {
      this.toastService.success('Produto criado com sucesso', 'Novo produto');
      this.routerService.navigate(['produtos'])
    }, ex => {
      this.toastService.error(ex.error.error);
    })
  }

  validaCampos(): boolean {
    return this.nome.valid && this.codBarra.valid &&
           this.qtdMin.valid && this.saldoInicial.valid
  }
}
