import { Injectable } from '@angular/core';
import { AlertController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(
    private alertController: AlertController
  ) { }

  presentAlert(title:string, message:string):Promise<string> {
    return new Promise(async (res,rej) =>{
      const alert = await this.alertController.create({
        header: title,
        message: message,
        buttons: [
          {
            text: 'Cancelar',
            role: 'cancel',
            handler: () => {
              // this.handlerMessage = 'Alert canceled';
            },
          },
          {
            text: 'Confirmar',
            role: 'confirm',
            handler: () => {
              // this.handlerMessage = 'Alert confirmed';
            },
          },
        ],
      });

      await alert.present();

      const { role } = await alert.onDidDismiss();
      res(role)
    })
  }

}
