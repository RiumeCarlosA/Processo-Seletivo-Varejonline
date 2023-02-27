import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gerente } from 'src/app/models/gerente';
import { GerenteService } from 'src/app/services/gerente.service';

@Component({
  selector: 'app-gerente-delete',
  templateUrl: './gerente-delete.component.html',
  styleUrls: ['./gerente-delete.component.css']
})
export class GerenteDeleteComponent {

  gerente: Gerente = {
    id:         '',
    nome:       '',
    usuario:      '',
    senha:      '',
    perfis:     [],
    dataCriacao: ''
  }

  constructor(
    private service: GerenteService,
    private toast:    ToastrService,
    private router:          Router,
    private route:   ActivatedRoute,
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

  delete(): void {
    this.service.delete(this.gerente.id).subscribe(() => {
      this.toast.success('Técnico deletado com sucesso', 'Delete');
      this.router.navigate(['gerentes'])
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
}
