import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { InventarioPageRoutingModule } from './inventario-routing.module';
import { InventarioPage } from './inventario.page';

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
    InventarioPageRoutingModule
    ,SharedModule
    // ,MatTableModule
  ],
  declarations: [
    InventarioPage
    ,ListadoComponent
    ,DetalleComponent
    ,RegistroComponent
  ]
})
export class InventarioPageModule {}
