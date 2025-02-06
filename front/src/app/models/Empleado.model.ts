import { GeneralParams } from "./GeneralParams.model";

export class Empleado extends GeneralParams{
  idEmpleado?: number = 0;
  nombre?: string = "";
  apellido?: string = "";
  idPuesto?: number = 0;
  puesto?: string = "";
}
