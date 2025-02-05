import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Inventario } from 'src/app/models/Inventario.model';
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
  SKU: string;
  // OBJETOS
  Form: FormGroup;
  inventario: Inventario;
  //LISTAS
  puestos: Puesto[] = [];
  //
  validationMessages = {
    sku: [
      { type: 'required', message: 'El sku es obligatorio' },
      { type: 'minlength', message: 'Mínimo 5 caracteres' },
      { type: 'maxlength', message: 'Máximo 5 caracteres' },
    ],
    nombre: [
      { type: 'required', message: 'El nombre es obligatorio' },
      { type: 'minlength', message: 'Mínimo 3 caracteres' },
      { type: 'maxlength', message: 'Máximo 250 caracteres' },
    ],
    cantidad: [
      { type: 'required', message: 'La cantidad es obligatoria' },
      { type: 'min', message: 'Mínimo 0' },
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
      nombre: new FormControl("",Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(250)])),
      cantidad: new FormControl("",Validators.compose([Validators.required, Validators.min(0)])),
    });
  }

  ngOnInit() {
    const state = window.history.state;
    this.editar = state?.hasOwnProperty("sku") ?? false;

    if( this.editar ){
      this.SKU = state?.sku ?? 0;

      if( this.SKU == "")
        this.router.navigateByUrl("/inventario");

      this.loadginSrv.present()
      .then(()=>{
        this.getDetail();
      })
    }


  }

  goBack(){
    this.router.navigateByUrl("/inventario");
  }

  onSubmit(){
    this.loadginSrv.present()
    .then(()=>{
      const value = this.Form.value;

      const obj:Inventario = {
        sku: this.editar? (this.inventario.sku ?? ""): (value?.sku ?? ""),
        nombre: value?.nombre ?? "",
        cantidad: value?.cantidad ?? 0
      }

      const promesa = this.editar? this.fetchSrv.request("PUT","inventario/Actualizar",obj)
        : this.fetchSrv.request("POST","inventario/Registrar",obj)

      promesa
      .then(r =>{
        if( r.meta.status == "OK" ){
          this.messageSrv.success(this.editar? "Inventario actualizado con exito": "Inventario registrado con exito");
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


  private getDetail(){
    this.fetchSrv.request("GET",`inventario/ObtenerPorSKU/${this.SKU}`,null)
    .then(r =>{
      if( r.meta.status == "OK"){
        this.inventario = r.data[0];
        this.Form.patchValue({
          sku: this.inventario.sku,
          nombre: this.inventario.nombre,
          cantidad: this.inventario.cantidad
        })

        this.Form.get("sku").disable();
      }else{
        this.messageSrv.error(r.data.idmensaje);
      }
    })
    .catch(e =>{

    })
    .finally(() => this.loadginSrv.dismiss());
  }

}
