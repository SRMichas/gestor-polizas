import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { EmpleadoPageRoutingModule } from './empleado-routing.module';
import { EmpleadoPage } from './empleado.page';

import { ListadoComponent } from './listado/listado.component'
import { DetalleComponent } from './detalle/detalle.component'
import { RegistroComponent } from './registro/registro.component'

import {SharedModule} from '../../shared/shared.module'
// import { MatTableModule } from '@angular/material/table';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EmpleadoPageRoutingModule
    ,SharedModule
    // ,MatTableModule
  ],
  declarations: [
    EmpleadoPage
    ,ListadoComponent
    ,DetalleComponent
    ,RegistroComponent
  ]
})
export class EmpleadoPageModule {}
