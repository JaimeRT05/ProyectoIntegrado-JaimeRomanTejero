import { bootstrapApplication } from '@angular/platform-browser';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router'; // 🔑 Para routerLink y router-outlet
import { AppComponent } from './app/app';
import { AppRoutingModule } from './app/app-routing.module';

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(
      HttpClientModule,
      RouterModule,        // 🔑 Incluye el RouterModule
      AppRoutingModule     // 🔑 Tus rutas
    )
  ]
});
