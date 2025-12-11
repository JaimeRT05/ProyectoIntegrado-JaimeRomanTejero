import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainContainerComponent } from './components/main-container.component/main-container.component';

const routes: Routes = [
  {
    path: '',
    component: MainContainerComponent,
    children: [
      // Rutas públicas
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
      },
      {
        path: 'oferta/:id',
        loadComponent: () =>
          import('./components/headers/header-publico.component/oferta-detalle.component/oferta-detalle.component').then(m => m.OfertaDetalleComponent)
      },

      // Rutas para ciudadanos
      {
        path: 'ciudadano',
        children: [
          {
            path: 'ofertas',
            loadComponent: () =>
              import('./components/headers/header-ciudadano.component/oferta-ciudadano.component/oferta-ciudadano.component').then(m => m.OfertaCiudadanoComponent)
          },
          {
            path: 'oferta/:id',
            loadComponent: () =>
              import('./components/headers/header-ciudadano.component/oferta-detalle-ciudadano.component/oferta-detalle-ciudadano.component').then(m => m.OfertaDetalleCiudadanoComponent)
          },
          {
            path: 'perfil',
            loadComponent: () =>
              import('./components/headers/header-ciudadano.component/perfil-ciudadano.component/perfil-ciudadano.component').then(m => m.PerfilCiudadanoComponent)
          },
          {
            path: 'editarPerfil',
            loadComponent: () =>
              import('./components/headers/header-ciudadano.component/editar-perfil-ciudadano.component/editar-perfil-ciudadano.component').then(m => m.EditarPerfilCiudadanoComponent)
          },
          { path: '', redirectTo: 'ofertas', pathMatch: 'full' }
        ]
      }
    ]
  },
  { path: '**', redirectTo: '' } // Ruta fallback
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
