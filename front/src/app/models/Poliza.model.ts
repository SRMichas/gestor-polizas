import { GeneralParams } from "./GeneralParams.model";

export class Poliza extends GeneralParams{
  id?: number = 0;
  empleadoID?: number = 0;
  empleado?: string = "";
  sku?: string = "";
  cantidad?: number = 0;
}
