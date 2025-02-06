import { GeneralParams } from "./GeneralParams.model";

export class Poliza extends GeneralParams{
  idPoliza?: number = 0;
  idEmpleado?: number = 0;
  empleado?: string = "";
  sku?: string = "";
  cantidad?: number = 0;
  inventario?: string = "";
}
