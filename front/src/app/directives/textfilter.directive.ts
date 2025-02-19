import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { InputFilterService } from '../services/input-filter.service';

@Directive({
  selector: '[appTextfilter]'
})

export class TextfilterDirective {

  @Input() appTextfilter = '';
  @Input() espacio = false;
  @Input() numero = false;

  constructor(
    private el: ElementRef,
    private inpFilt: InputFilterService
  ) {

  }

  @HostListener('keypress',['$event']) keypress(event) {

    switch(this.appTextfilter){
      case "num":
        this.inpFilt.soloNumeros(event);
        break;
      case "alfa":
        this.inpFilt.alfaNumerico(event,true,-1,this.espacio);
        break;
      case "rfc":
        this.inpFilt.rfc(event,false);
        break;
      default: break;
    }
  }

  @HostListener('paste',['$event']) paste(event) {
    setTimeout(() => {
      switch(this.appTextfilter){
        case "num":
          this.inpFilt.soloNumeros(event,this.el);
          break;
        case "alfa":
          this.inpFilt.alfaNumerico(event,true,-1,this.espacio,this.el);
          break;
        case "rfc":
          this.inpFilt.rfc(event,true,this.el);
          break;
        default: break;
      }
    }, 2);
  }

  @HostListener('change', ['$event']) onChange(event) {
    setTimeout(() => {
      switch(this.appTextfilter){
        case "num":
          this.inpFilt.soloNumeros(event,this.el);
          break;
        case "alfa":
          this.inpFilt.alfaNumerico(event,true,-1,this.espacio,this.el);
          break;
        case "rfc":
          this.inpFilt.rfc(event,true,this.el);
          break;
        default: break;
      }
    }, 2);
  }


}
