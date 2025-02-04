import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
//Modules
import { ElLoadingComponentModule } from "../views/components/el-loading/el-loading.module";
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
//Plugins
//Components

import {ElLoadingComponent} from '../views/components/el-loading/el-loading.component';
//directives

@NgModule({
  declarations: [
    ElLoadingComponent
  ],
  exports: [
    CommonModule
    ,ReactiveFormsModule
    ,ElLoadingComponent
    ,MatIconModule
    ,MatPaginatorModule
    ,MatTableModule
    ,MatTooltipModule
    ,MatInputModule
    ,MatFormFieldModule
    ,MatDividerModule
    ,MatButtonModule
    ,MatSelectModule
  ],
  imports: [
    CommonModule
    ,FormsModule
    ,IonicModule
    ,ReactiveFormsModule
    ,RouterModule
    ,ElLoadingComponentModule
    ,MatIconModule
    ,MatPaginatorModule
    ,MatTableModule
    ,MatTooltipModule
    ,MatInputModule
    ,MatFormFieldModule
    ,MatDividerModule
    ,MatButtonModule
    ,MatSelectModule
  ]
})
export class SharedModule { }
