import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Poliza } from 'src/app/models/Poliza.model';
import { Empleado } from 'src/app/models/Empleado.model';
import { FetchService } from 'src/app/services/fetch.service';
import { LoadingService } from 'src/app/services/loading.service';
import { MessagingService } from 'src/app/services/messaging.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
})
export class RegistroComponent implements OnInit {

  // VARIABLES
  editar: boolean;
  polizaID: number;
  // OBJETOS
  Form: FormGroup;
  empleado: Poliza;
  //LISTAS
  empleados: Empleado[] = [];
  //
  validationMessages = {
    cantidad: [
      { type: 'required', message: 'El nombre es obligatorio' },
      { type: 'min', message: 'Mínimo 0' },
    ],
    sku: [
      { type: 'required', message: 'El sku es obligatorio' },
      { type: 'minlength', message: 'Mínimo 5 caracteres' },
      { type: 'maxlength', message: 'Máximo 5 caracteres' },
    ],
    empleado: [
      { type: 'required', message: 'El empleado es obligatorio' }
    ],
  };


  constructor(
    private formBuilder: FormBuilder,
    private messageSrv: MessagingService,
    private loadginSrv: LoadingService,
    private router: Router,
    private route:ActivatedRoute,
    private fetchSrv: FetchService,
    private utilSrv: UtilService
  ) {
    this.Form = this.formBuilder.group({
      sku: new FormControl("",Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(5)])),
      cantidad: new FormControl("",Validators.compose([Validators.required, Validators.min(0)])),
      empleado: new FormControl("",Validators.compose([Validators.required])),
    });
  }

  ngOnInit() {
    const state = window.history.state;
    this.editar = state?.hasOwnProperty("id") ?? false;
    this.getEmpleados();
    if( this.editar ){
      this.polizaID = state?.id ?? 0;

      if( this.polizaID == 0)
        this.router.navigateByUrl("/poliza");

      this.loadginSrv.present()
      .then(()=>{
        this.getDetail();
      })
    }


  }

  goBack(){
    this.router.navigateByUrl("/poliza");
  }

  onSubmit(){
    this.loadginSrv.present()
    .then(()=>{
      const value = this.Form.value;

      const obj:Poliza = {
        sku: value?.sku ?? "",
        cantidad: value?.cantidad ?? 0,
        empleadoID: value?.empleado ?? 0
      }

      const promesa = this.editar? this.fetchSrv.request("PUT","poliza/Actualizar",obj)
        : this.fetchSrv.request("POST","poliza/Registrar",obj)

      promesa
      .then(r =>{
        if( r.meta.status == "OK" ){
          this.messageSrv.success(this.editar? "Poliza actualizado con exito": "Poliza registrado con exito");
          setTimeout(() => {
            this.goBack();
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

  //fetch
  private getEmpleados(){
    this.fetchSrv.request("GET", "empleado/obtenerActivos",null)
    .then( r =>{
      if( r.meta.status == "OK"){
        this.empleados = r.data;
      }else{
        this.messageSrv.error(r.data.IDMensaje);
      }
    })
    .catch(e => {

    });
  }


  private getDetail(){
    this.fetchSrv.request("GET",`poliza/ObtenerPorId/${this.polizaID}`,null)
    .then(r =>{
      if( r.meta.status == "OK"){
        this.empleado = r.data[0];
        this.Form.patchValue({
          sku: this.empleado.sku,
          cantidad: this.empleado.cantidad,
          empleado: this.empleado.id
        })
      }else{
        this.messageSrv.error(r.data.idmensaje);
      }
    })
    .catch(e =>{

    })
    .finally(() => this.loadginSrv.dismiss());
  }

}
