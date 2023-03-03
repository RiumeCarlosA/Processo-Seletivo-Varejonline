import { Component, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimentacao } from 'src/app/models/movimentacao';
import { MovimentacaoService } from 'src/app/services/movimentacao.service';

@Component({
  selector: 'app-estoque-list',
  templateUrl: './estoque-list.component.html',
  styleUrls: ['./estoque-list.component.css']
})
export class EstoqueListComponent {
  
  ELEMENT_DATA: Movimentacao[] = []

  FILTERED_DATA: Movimentacao[] = []

  displayedColumns: string[] = ['id', 'quantidade', 'nomePessoa', 'nomeProduto', 'dataMovimentacao', 'movimentos', 'acoes'];
  dataSource = new MatTableDataSource<Movimentacao>(this.ELEMENT_DATA);

  prioridade: any = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: MovimentacaoService
  ) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<Movimentacao>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
