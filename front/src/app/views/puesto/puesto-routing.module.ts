import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PuestoPage } from './puesto.page';
import { ListadoComponent } from './listado/listado.component';
import { RegistroComponent } from './registro/registro.component';

const routes: Routes = [
  {
    path: '',
    component: PuestoPage,
    children: [
      {
        path: '',
        redirectTo: 'listado',
        pathMatch: 'full'
      },
      {
        path: 'listado',
        component: ListadoComponent
      },
      {
        path: 'registro',
        component: RegistroComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PuestoPageRoutingModule {}
