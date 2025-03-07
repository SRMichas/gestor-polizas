// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  url: 'http://localhost:9000/api'
  ,apiPuesto: "http://localhost:9004/api"
  ,apiEmpleado: "http://localhost:9001/api"
  ,apiInventario: "http://localhost:9002/api"
  ,apiPoliza: "http://localhost:9003/api"
  ,production: false,
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
