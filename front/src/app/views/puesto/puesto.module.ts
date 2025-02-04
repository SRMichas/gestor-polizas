import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PuestoPageRoutingModule } from './puesto-routing.module';
import { PuestoPage } from './puesto.page';

import { ListadoComponent } from './listado/listado.component'
import { DetalleComponent } from './detalle/detalle.component'
import { RegistroComponent } from './registro/registro.component'

import {SharedModule} from '../../shared/shared.module'

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PuestoPageRoutingModule
    ,SharedModule
    // ,MatTableModule
  ],
  declarations: [
    PuestoPage
    ,ListadoComponent
    ,DetalleComponent
    ,RegistroComponent
  ]
})
export class PuestoPageModule {}
