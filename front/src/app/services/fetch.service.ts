import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiResponse } from '../models/ApiResponse.model';

@Injectable({
  providedIn: 'root'
})
export class FetchService {

  private url = environment.url;

  constructor() { }

  public request(method:string, controller:string, params?:any): Promise<ApiResponse>{
    return new Promise((resolve, reject) => {
      try{
        fetch(`${this.url}/${controller}`, {
          method: method,
          headers: {
          'Content-Type': 'application/json',
          }
          ,body: params? JSON.stringify(params) : null
        }).then(async (response) => {
          if (response.status === 200) {
            response.json().then((data: ApiResponse) => {
              if (Object.entries(data).length === 0) {
                resolve(null);
              } else {
                resolve(data);
              }
            });
          } else {
            reject('No se puedo completar la peticion');
          }
        }).catch(e => reject(e));
      }catch(e){
        reject(e);
      }
    })
  }
}
