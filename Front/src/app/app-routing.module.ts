import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainContainerComponent } from './components/main-container.component/main-container.component';

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
      },
      {
        path: 'oferta/:id',
        loadComponent: () =>
          import('./components/headers/header-publico.component/oferta-detalle.component/oferta-detalle.component').then(m => m.OfertaDetalleComponent)
      },

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
          {
            path: 'mis-postulaciones',
            loadComponent: () =>
              import('./components/headers/header-ciudadano.component/mis-postulaciones.component/mis-postulaciones.component').then(m => m.MisPostulacionesComponent)
          },
          { path: '', redirectTo: 'ofertas', pathMatch: 'full' }
        ]
      },

      {
        path: 'empresa',
        children: [

          {
            path: 'crear-oferta',     
            loadComponent: () =>
              import('./components/headers/header-empresa.component/crear-oferta.component/crear-oferta.component').then(m => m.CrearOfertaComponent)
          },
          {
            path: 'gestionar-ofertas',     
            loadComponent: () =>
              import('./components/headers/header-empresa.component/gestionar-oferta.component/gestionar-oferta.component').then(m => m.GestionarOfertasComponent)
          },

          {
            path: 'perfil',
            loadComponent: () =>
              import('./components/headers/header-empresa.component/perfil-empresa.component/perfil-empresa.component').then(m => m.PerfilEmpresaComponent)
          },
          {
            path: 'editarPerfil',
            loadComponent: () =>
              import('./components/headers/header-empresa.component/editar-perfil-empresa.component/editar-perfil-empresa.component').then(m => m.EditarPerfilEmpresaComponent)
          },
          {
            path: 'candidatos',
            loadComponent: () =>
              import('./components/headers/header-empresa.component/candidatos-oferta.component/candidatos-oferta.component').then(m => m.CandidatosOfertaComponent)
          },
          { path: '', redirectTo: 'ofertas', pathMatch: 'full' }
        ]
      },

      {
        path: 'admin',
        children: [
          
          {
            path: 'ciudadanos',
            loadComponent: () =>
              import('./components/headers/header-admin.component/admin-ciudadanos.component/admin-ciudadanos.component').then(m => m.AdminCiudadanosComponent)
          },
          
          {
            path: 'empresas',
            loadComponent: () =>
              import('./components/headers/header-admin.component/admin-empresas.component/admin-empresas.component').then(m => m.AdminEmpresasComponent)
          },
          
          {
            path: 'ofertas',
            loadComponent: () =>
              import('./components/headers/header-admin.component/admin-ofertas.component/admin-ofertas.component').then(m => m.AdminOfertasComponent)
          },
          { path: '', redirectTo: 'ciudadanos', pathMatch: 'full' }
        ]
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
