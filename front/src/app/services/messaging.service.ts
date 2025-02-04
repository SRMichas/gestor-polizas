import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})

export class MessagingService {

  constructor(
    private toastController: ToastController
  ) { }

  async warning(message: string) {
    return this.show(message, 'warning');
  }

  async error(message: string) {
    return this.show(message, 'danger');
  }

  async success(message: string) {
    return this.show(message, 'success');
  }

  async info(message: string) {
    return this.show(message, 'medium');
  }

  private async show(message: string, type: string) {
    const toast = await this.toastController.create({
      message,
      duration: 2000,
      color: type,
      position : 'bottom'
    });
    toast.present();
  }
}
