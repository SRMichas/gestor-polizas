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
  puestoID: number;
  // OBJETOS
  Form: FormGroup;
  puesto: Puesto;
  //LISTAS
  puestos: Puesto[] = [];
  //
  validationMessages = {
    nombre: [
      { type: 'required', message: 'El nombre es obligatorio' },
      { type: 'minlength', message: 'Mínimo 3 caracteres' },
      { type: 'maxlength', message: 'Máximo 250 caracteres' },
    ]
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
      // apellido: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3),Validators.maxLength(250)])),
      // puesto: new FormControl("",Validators.compose([Validators.required])),
    });
  }

  ngOnInit() {
    const state = window.history.state;
    this.editar = state?.hasOwnProperty("id") ?? false;
    if( this.editar ){
      this.puestoID = state?.id ?? 0;

      if( this.puestoID == 0)
        this.router.navigateByUrl("/puesto");

      this.loadginSrv.present()
      .then(()=>{
        this.getDetail();
      })
    }


  }

  goBack(){
    this.router.navigateByUrl("/puesto");
  }

  onSubmit(){
    this.loadginSrv.present()
    .then(()=>{
      const value = this.Form.value;

      const obj:Puesto = {
        id: this.puestoID,
        nombre: value?.nombre ?? "",
      }

      const promesa = this.editar? this.fetchSrv.request("PUT","puesto/Actualizar",obj)
        : this.fetchSrv.request("POST","puesto/Registrar",obj)

      promesa
      .then(r =>{
        if( r.meta.status == "OK" ){
          this.messageSrv.success(this.editar? "Puesto actualizado con exito": "Puesto registrado con exito");
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

  private getDetail(){
    this.fetchSrv.request("GET",`puesto/ObtenerPorId/${this.puestoID}`,null)
    .then(r =>{
      if( r.meta.status == "OK"){
        this.puesto = r.data[0];
        this.Form.patchValue({
          nombre: this.puesto.nombre
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
