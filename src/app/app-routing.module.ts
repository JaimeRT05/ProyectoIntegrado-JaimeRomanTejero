import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainContainerComponent } from './components/main-container.component/main-container.component';
import { InicioComponent } from './components/inicio.component/inicio.component';

const routes: Routes = [
  {
    path: '',
    component: MainContainerComponent,
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./components/inicio.component/inicio.component').then(m => m.InicioComponent)
      },
      {
        path: 'empresasPublico',
        loadComponent: () =>
          import('./components/headers/header-publico.component/empresas-publico.component/empresas-publico.component').then(m => m.EmpresasPublicoComponent)
      },
      {
        path: 'ofertasPublico',
        loadComponent: () =>
          import('./components/headers/header-publico.component/ofertas-publico.component/ofertas-publico.component').then(m => m.OfertasPublicoComponent)
      }
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
