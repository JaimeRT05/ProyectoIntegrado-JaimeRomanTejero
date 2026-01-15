import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginModalService {
  private _modalAbierto = new BehaviorSubject<boolean>(false);
  modalAbierto$ = this._modalAbierto.asObservable();

  abrirModal() {
    this._modalAbierto.next(true);
  }

  cerrarModal() {
    this._modalAbierto.next(false);
  }
}
    