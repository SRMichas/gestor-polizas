import { GeneralParams } from "./GeneralParams.model";

export class Inventario extends GeneralParams{
  sku?: string = "";
  nombre?: string = "";
  cantidad?: number = 0;
  idPoliza?: number = 0;
}
