import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from 'src/app/models/Empleado.model';
import { Puesto } from 'src/app/models/Puesto.model';
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
  empleadoID: number;
  // OBJETOS
  Form: FormGroup;
  empleado: Empleado;
  //LISTAS
  puestos: Puesto[] = [];
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
    puesto: [
      { type: 'required', message: 'El nombre es obligatorio' }
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
      nombre: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(250)])),
      apellido: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3),Validators.maxLength(250)])),
      puesto: new FormControl("",Validators.compose([Validators.required])),
    });
  }

  ngOnInit() {
    const state = window.history.state;
    let editar = state?.hasOwnProperty("id") ?? false;
    this.getPuestos();
    if( editar ){
      this.empleadoID = state?.id ?? 0;

      if( this.empleadoID == 0)
        this.router.navigateByUrl("/empleado");

      this.loadginSrv.present()
      .then(()=>{
        this.getDetail();
      })
    }


  }

  goBack(){
    this.router.navigateByUrl("/empleado");
  }

  onSubmit(){
    this.loadginSrv.present()
    .then(()=>{
      const value = this.Form.value;

      const obj:Empleado = {
        nombre: value?.nombre ?? "",
        apellido: value?.apellido ?? "",
        puestoID: value?.puesto ?? 0
      }

      const promesa = this.editar? this.fetchSrv.request("PUT","empleado/Actualizar",obj)
        : this.fetchSrv.request("POST","empleado/Registrar",obj)

      promesa
      .then(r =>{
        if( r.meta.status == "OK" ){
          this.messageSrv.success(this.editar? "Empleado actualizado con exito": "Empleado registrado con exito");
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
  private getPuestos(){
    this.fetchSrv.request("GET", "puesto/obtenerActivos",null)
    .then( r =>{
      if( r.meta.status == "OK"){
        this.puestos = r.data;
      }else{
        this.messageSrv.error(r.data.IDMensaje);
      }
    })
    .catch(e => {

    });
  }


  private getDetail(){
    this.fetchSrv.request("GET",`empleado/ObtenerPorId/${this.empleadoID}`,null)
    .then(r =>{
      if( r.meta.status == "OK"){
        this.empleado = r.data[0];
        this.Form.patchValue({
          nombre: this.empleado.nombre,
          apellido: this.empleado.apellido,
          puesto: this.empleado.puestoID
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
