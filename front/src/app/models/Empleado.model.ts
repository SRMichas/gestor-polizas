import { GeneralParams } from "./GeneralParams.model";

export class Empleado extends GeneralParams{
  id?: number = 0;
  nombre?: string = "";
  apellido?: string = "";
  puestoID?: number = 0;
  puesto?: string = "";
}
