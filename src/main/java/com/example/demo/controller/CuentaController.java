package com.example.demo.controller;

import com.example.demo.dto.CuentaDto;
import com.example.demo.dto.Response;

import com.example.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
@Controller
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CuentaDto>> listarCuenta() throws Exception {
        List<CuentaDto> lsCuenta = cuentaService.listarCuenta();
        if(lsCuenta == null) {
            throw new Exception("NO HAY REGISTRO");
        }
        return new ResponseEntity<List<CuentaDto>>(lsCuenta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> crearCuenta(@RequestBody CuentaDto cuenta) {
        try {

            Response response= cuentaService.crearCuenta(cuenta);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> actualizarCuenta(@PathVariable("id") int id, @RequestBody CuentaDto cuenta) {
        try {

            Response response= cuentaService.actualizarCuenta(id, cuenta);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCuenta(@PathVariable("id") int id) {
        try {

            Response response= cuentaService.eliminarCuenta(id);

            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
