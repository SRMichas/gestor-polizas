import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PolizaPageRoutingModule } from './poliza-routing.module';
import { PolizaPage } from './poliza.page';

import { ListadoComponent } from './listado/listado.component'
import { RegistroComponent } from './registro/registro.component'

import {SharedModule} from '../../shared/shared.module'

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PolizaPageRoutingModule
    ,SharedModule
    // ,MatTableModule
  ],
  declarations: [
    PolizaPage
    ,ListadoComponent
    ,RegistroComponent
  ]
})
export class PolizaPageModule {}
