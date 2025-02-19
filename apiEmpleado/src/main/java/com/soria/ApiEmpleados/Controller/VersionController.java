package com.soria.ApiEmpleados.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/version")
@CrossOrigin("*")
public class VersionController {

    @GetMapping("/checkVersion")
    public ResponseEntity<String> getVersion(){
        return  new ResponseEntity<>("0.0.1", HttpStatus.OK);
    }
}
