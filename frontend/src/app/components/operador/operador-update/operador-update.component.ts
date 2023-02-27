import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Operador } from 'src/app/models/operador';
import { OperadorService } from 'src/app/services/operador.service';

@Component({
  selector: 'app-operador-update',
  templateUrl: './operador-update.component.html',
  styleUrls: ['./operador-update.component.css']
})
export class OperadorUpdateComponent {

  operador: Operador = {
    id:         '',
    nome:       '',
    usuario:      '',
    senha:      '',
    perfis:     [],
    dataCriacao: ''
  }

  nome: FormControl = new FormControl(null, Validators.minLength(3));
  usuario: FormControl = new FormControl(null, Validators.minLength(3));
  senha: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(
    private service: OperadorService,
    private toast:     ToastrService,
    private router:           Router,
    private route:    ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.operador.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }
  
  findById(): void {
    this.service.findById(this.operador.id).subscribe(resposta =>{
      resposta.perfis = []
      this.operador = resposta;
    })
  }

  update(): void {
    this.service.update(this.operador).subscribe(() => {
      this.toast.success('Operador atualizado com sucesso', 'Update');
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
