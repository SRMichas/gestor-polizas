import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/Empleado.model';
import { FetchService } from 'src/app/services/fetch.service';
import { LoadingService } from 'src/app/services/loading.service';
import { MessagingService } from 'src/app/services/messaging.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.scss'],
})
export class ListadoComponent implements OnInit {

  //VARAIBLES
  busqueda: string;
  pagina: number = 1
  totalPaginas = 1;
  paginado = 20
  //LISTAS
  listGlobal: any = [];
  listFlt: any = [];
  columns: string[] = ['Nombre', 'acciones'];

  constructor(
    private messageSrv: MessagingService,
    private loadginSrv: LoadingService,
    private router: Router,
    private fetchSrv: FetchService,
    private utilSrv: UtilService
  ) { }

  ngOnInit() {
    this.searchItems();
  }

  private fetchItems():void{
    const flt:Empleado = {
      busqueda: this.busqueda,
      pagina: this.pagina,
      paginado: this.paginado
    }

    this.fetchSrv.request("POST","puesto/ObtenerPaginado",flt)
    .then(r =>{
      if( r.meta.status == "OK" ){
        const emp:Empleado = r.data[0];
        this.listGlobal = r.data
        this.listFlt = r.data
        this.totalPaginas = emp?.totalPaginas ?? 1;
      }
      else{

      }
    })
    .catch(e =>{

    })
    .finally(()=> {
      if( this.loadginSrv.isLoading )
        this.loadginSrv.dismiss()
    })
  }

  searchItems():void{
    this.loadginSrv.present()
    .then(()=>{
      this.fetchItems();
    })
  }

  resetSearchBox():void{
    this.busqueda = "";
    this.searchItems();
  }

  //PAGINADO
  public irAInicio(): void{
    if( this.pagina > 1){
      this.pagina = 1;
      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, this.paginado);
    }
  }

  public avanzar(atras): void{
    this.pagina += atras? -1 : 1;
    let realizarBusqueda = true;

    if( atras ){
      realizarBusqueda = !(this.pagina < 1);
      this.pagina = this.pagina < 1? 1 : this.pagina;
    }else{
      realizarBusqueda = !(this.pagina > this.totalPaginas);
      this.pagina = this.pagina > this.totalPaginas? this.totalPaginas : this.pagina;
    }

    if( !realizarBusqueda)
      return;

      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, this.pagina * this.paginado);
  }

  public irAFin(): void{
    if( this.pagina < (this.totalPaginas??1) ){
      this.pagina = this.totalPaginas??1;
      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, -1);
    }
  }

  public irAPagina(): void{
    let aux = this.pagina??1;

    if( aux == 0 || aux > this.totalPaginas)
      this.pagina = 1;

      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, this.pagina * this.paginado);
  }
  //
  irDetalle(valor) {
    console.log(valor);
    this.router.navigateByUrl('/puesto/registro',{state: valor});
  }

  eliminar(valor) {
    console.log(valor);
    this.utilSrv.presentAlert("Eliminar Puesto","¿Desea eliminar este registro?")
    .then( r =>{
      if( r == "confirm"){
        this.loadginSrv.present()
        .then(()=>{
          this.fetchSrv.request("DELETE","puesto/Eliminar",valor)
          .then(r =>{
            if(r.meta.status == "OK"){
              this.messageSrv.success("Puesto eliminado exitosamente");
              this.fetchItems();
            }else{
              this.messageSrv.error(r.data.IDMensaje);
            }
          })
          // .catch(e =>{
          //   this.messageSrv.error()
          // })

        })
      }else{

      }
    }).catch(e =>{})
  }

  public onlyNumbers(event){
    let code = event.charCode;
    if( code < 48 || code > 57 ){
      event.preventDefault();
    }
  }

  //

  crear():void{
    this.router.navigateByUrl('/puesto/registro');
  }
}
