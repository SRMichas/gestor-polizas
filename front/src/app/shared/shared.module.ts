import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
//Modules
import { ElLoadingComponentModule } from "../views/components/el-loading/el-loading.module";
//Plugins
//Components

import {ElLoadingComponent} from '../views/components/el-loading/el-loading.component';
//directives

@NgModule({
  declarations: [
    ElLoadingComponent
  ],
  exports: [
    ElLoadingComponent
  ],
  imports: [
    CommonModule
    ,FormsModule
    ,IonicModule
    ,ReactiveFormsModule
    ,RouterModule
  ]
})
export class SharedModule { }
