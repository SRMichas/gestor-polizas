import { ElementRef, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class InputFilterService {

  constructor() { }

  public soloNumeros(event: any,elem?: ElementRef){
    let code = !elem? event.keyCode : event.target.value;

    if(
      (!elem && (code < 48 || code > 57)) ||
      (elem && /[^0-9]+/.test(code))
    ){
      event.preventDefault();
      event.stopPropagation();
      if( elem )
        elem.nativeElement.value = "";
    }

  }


  public alfaNumerico(event: any, mayusculas: boolean, limite?: number, aceptaEspacio?: boolean, elem?: ElementRef){
    limite = limite || 0;
    let pattern = aceptaEspacio? /[^a-zA-Z0-9 ]/g : /[^a-zA-Z0-9]/g;
    let inputValue:string = !elem? event.key : event.target.value;

    if( pattern.test(inputValue)){
      event.preventDefault();
      event.stopPropagation();
      if( elem )
      	elem.nativeElement.value = "";
      return;
    }

    if( mayusculas )
      inputValue = event.target.value + inputValue;

    if( limite > 0)
      inputValue = inputValue.substring(0,limite);
  }

  public rfc(event: any, isPaste: boolean, el?: ElementRef){
    let code = !isPaste? event.key: event.target.value;
    const regex = /^[a-zA-Z0-9&]+$/;

    if( !regex.test(code) ){
      event.preventDefault();
      event.stopPropagation();
      if( isPaste )
        el.nativeElement.value = "";
    }
  }

}
