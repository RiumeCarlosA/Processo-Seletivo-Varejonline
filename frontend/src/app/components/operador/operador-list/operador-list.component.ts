import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Operador } from 'src/app/models/operador';
import { OperadorService } from 'src/app/services/operador.service';

@Component({
  selector: 'app-operador-list',
  templateUrl: './operador-list.component.html',
  styleUrls: ['./operador-list.component.css']
})
export class OperadorListComponent {

  ELEMENT_DATA: Operador[] = []

  displayedColumns: string[] = ['nome', 'usuario', 'acoes'];
  dataSource = new MatTableDataSource<Operador>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: OperadorService
  ) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe( resposta => {
      this.ELEMENT_DATA = resposta
      this.dataSource = new MatTableDataSource<Operador>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
