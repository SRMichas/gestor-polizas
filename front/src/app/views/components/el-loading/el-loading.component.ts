import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'el-loading',
  templateUrl: './el-loading.component.html',
  styleUrls: ['./el-loading.component.scss'],
})
export class ElLoadingComponent implements OnInit {


  //
  @Input() show: boolean = false;

  constructor() { }

  ngOnInit() {}

}
