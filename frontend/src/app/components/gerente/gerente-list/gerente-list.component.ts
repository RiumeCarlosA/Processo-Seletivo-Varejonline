import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Gerente } from 'src/app/models/gerente';
import { GerenteService } from 'src/app/services/gerente.service';

@Component({
  selector: 'app-gerente-list',
  templateUrl: './gerente-list.component.html',
  styleUrls: ['./gerente-list.component.css']
})
export class GerenteListComponent {

  ELEMENT_DATA: Gerente[] = []

  displayedColumns: string[] = ['id', 'nome', 'usuario', 'acoes'];
  dataSource = new MatTableDataSource<Gerente>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: GerenteService
  ) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe( resposta => {
      this.ELEMENT_DATA = resposta
      this.dataSource = new MatTableDataSource<Gerente>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
