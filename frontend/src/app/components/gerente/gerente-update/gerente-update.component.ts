import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gerente } from 'src/app/models/gerente';
import { GerenteService } from 'src/app/services/gerente.service';

@Component({
  selector: 'app-gerente-update',
  templateUrl: './gerente-update.component.html',
  styleUrls: ['./gerente-update.component.css']
})
export class GerenteUpdateComponent {

  gerente: Gerente = {
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
    private service: GerenteService,
    private toast:     ToastrService,
    private router:           Router,
    private route:    ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.gerente.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }
  
  findById(): void {
    this.service.findById(this.gerente.id).subscribe(resposta =>{
      resposta.perfis = []
      this.gerente = resposta;
    })
  }

  update(): void {
    this.service.update(this.gerente).subscribe(() => {
      this.toast.success('Gerente atualizado com sucesso', 'Update');
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
