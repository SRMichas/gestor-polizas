import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { TabsComponent } from './views/components/tabs/tabs.component';

const routes: Routes = [
  {
    path: '',
    component: TabsComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'empleado',
      },
      {
        path: 'empleado',
        loadChildren: () => import('./views/empleado/empleado.module').then((m) => m.EmpleadoPageModule),
      },
      {
        path: 'puesto',
        loadChildren: () => import('./views/puesto/puesto.module').then((m) => m.PuestoPageModule),
      },
      {
        path: 'inventario',
        loadChildren: () => import('./views/inventario/inventario.module').then((m) => m.InventarioPageModule),
      },
      // {
      //   path: 'radio',
      //   loadChildren: () => import('./radio/radio-page.module').then((m) => m.RadioPageModule),
      // },
      // {
      //   path: 'library',
      //   loadChildren: () => import('./library/library-page.module').then((m) => m.LibraryPageModule),
      // },
      // {
      //   path: 'search',
      //   loadChildren: () => import('./search/search-page.module').then((m) => m.SearchPageModule),
      // },
    ],
  },
  // {
  //   path: 'home',
  //   loadChildren: () =>
  //     import('./home/home.module').then((m) => m.HomePageModule),
  // },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
