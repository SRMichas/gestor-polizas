import { Injectable } from '@angular/core';
import { LoadingController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})

export class LoadingService {

  isLoading = false;
  loading;
  constructor(
    public loadingController: LoadingController
  ) { }

  async present(duracion?) {
    if (!this.isLoading) {
      this.isLoading = true;
      return await this.loadingController.create({
        duration: duracion ?? 120000,
        message: 'Espere un momento...'
      }).then(a => {
        a.present().then(() => {
          if (!this.isLoading) {
            a.dismiss();
          }
        });
      });
    }
  }

  async dismiss() {
    if (this.isLoading) {
      this.isLoading = false;
      return await this.loadingController.dismiss();
    }
  }

  async presentLoading(si,mensaje) {
    if(si){
      this.loading = await this.loadingController.create({
        cssClass: 'my-custom-class',
        message: mensaje
      });
      await this.loading.present();
    }else{
      await this.loading.dismiss();
    }
  }
}
