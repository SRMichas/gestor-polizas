import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IonModal } from '@ionic/angular';
import { Empleado } from 'src/app/models/Empleado.model';
import { Poliza } from 'src/app/models/Poliza.model';
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

  //REFERENCIAS
  @ViewChild(IonModal) modal: IonModal;
  //VARAIBLES
  busqueda: string;
  pagina: number = 1
  totalPaginas = 1;
  paginado = 20
  // OBJETOS
  Form: FormGroup;
  empleado: Empleado;
  //LISTAS
  listGlobal: Poliza[] = [];
  listFlt: Poliza[] = [];
  columns: string[] = ['Empleado', 'SKU', 'Cantidad', 'acciones'];
  //
  validationMessages = {
    nombre: [
      { type: 'required', message: 'El nombre es obligatorio' },
      { type: 'minlength', message: 'Mínimo 3 caracteres' },
      { type: 'maxlength', message: 'Máximo 250 caracteres' },
    ],
    apellido: [
      { type: 'required', message: 'El apellido es obligatorio' },
      { type: 'minlength', message: 'Mínimo 3 caracteres' },
      { type: 'maxlength', message: 'Máximo 250 caracteres' },
    ],
  };

  constructor(
    private formBuilder: FormBuilder,
    private messageSrv: MessagingService,
    private loadginSrv: LoadingService,
    private router: Router,
    private fetchSrv: FetchService,
    private utilSrv: UtilService
  ) {
    this.Form = this.formBuilder.group({
      nombre: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(250)])),
      apellido: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3),Validators.maxLength(250)])),
    });
  }

  ngOnInit() {
    this.searchItems();
  }

  private fetchItems():void{
    const flt:Poliza = {
      busqueda: this.busqueda,
      pagina: this.pagina,
      paginado: this.paginado
    }

    this.fetchSrv.request("POST","poliza/ObtenerPaginado",flt)
    .then(r =>{
      if( r.meta.status == "OK" ){
        const emp:Poliza = r.data[0];
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
      this.fetchItems();
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
      this.fetchItems();
  }

  public irAFin(): void{
    if( this.pagina < (this.totalPaginas??1) ){
      this.pagina = this.totalPaginas??1;
      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, -1);
      this.fetchItems();
    }
  }

  public irAPagina(): void{
    let aux = this.pagina??1;

    if( aux == 0 || aux > this.totalPaginas)
      this.pagina = 1;

      this.listFlt = this.listGlobal.slice((this.pagina-1) * this.paginado, this.pagina * this.paginado);
      this.fetchItems();
  }
  //
  irDetalle(valor) {
    console.log(valor);
    this.router.navigateByUrl('/poliza/registro',{state: valor});
  }

  eliminar(valor) {
    console.log(valor);
    this.utilSrv.presentAlert("Eliminar Poliza","¿Desea eliminar este registro?")
    .then( r =>{
      if( r == "confirm"){
        this.loadginSrv.present()
        .then(()=>{
          this.fetchSrv.request("DELETE","poliza/Eliminar",valor)
          .then(r =>{
            if(r.meta.status == "OK"){
              this.messageSrv.success("Poliza eliminado exitosamente");
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
    this.router.navigateByUrl('/poliza/registro');
  }

  abrirModal(poliza:Poliza):void{
    this.modal.present()
    .then(() =>{
      this.loadginSrv.present()
      .then(()=>{
        this.fetchSrv.request("GET",`empleado/ObtenerPorId/${poliza.idEmpleado}`,null)
        .then( r =>{
          if( r.meta.status == "OK" ){
            this.empleado = r.data[0];
            this.Form.patchValue({
              nombre: this.empleado.nombre,
              apellido: this.empleado.apellido
            })
          }else{
            this.messageSrv.error(r.data.idmensaje)
          }
        })
        .catch(e =>{
          this.messageSrv.error("Error al obtener el empleado")
        })
        .finally(()=> this.loadginSrv.dismiss());
      })
    })

  }

  cerrar() {
    this.modal.dismiss(null, 'cancel');
  }

  actualizarCliente():void{
    this.loadginSrv.present()
    .then(()=>{
      const value = this.Form.value;

      const obj:Empleado = {
        idEmpleado: this.empleado.idEmpleado,
        nombre: value?.nombre ?? "",
        apellido: value?.apellido ?? "",
      }

      this.fetchSrv.request("PUT","empleado/Actualizar",obj)
      .then(r =>{
        if( r.meta.status == "OK" ){
          this.messageSrv.success("Empleado actualizado con exito");
          setTimeout(() => {
            this.modal.dismiss(null,"confirm")
          }, 1000);
        }else{
          this.messageSrv.error(r.data.idmensaje)
        }
      })
      .catch(e =>{

      })
      .finally(() => this.loadginSrv.dismiss() )
    })
  }

  manejaModal(event: any):void{
    // const ev = event as CustomEvent<OverlayEventDetail<string>>;
    if (event.detail.role === 'confirm') {
      this.fetchItems();
    }
  }
}
