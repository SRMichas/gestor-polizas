import { GeneralParams } from "./GeneralParams.model";

export class Empleado extends GeneralParams{
  Id?: number;
  Nombre?: string;
  Apellido?: string;
  PuestoID?: number;
  Puesto?: string;
}
