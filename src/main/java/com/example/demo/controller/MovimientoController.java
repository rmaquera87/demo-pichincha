package com.example.demo.controller;

import com.example.demo.dto.CuentaDto;
import com.example.demo.dto.MovimientoDto;
import com.example.demo.dto.Response;
import com.example.demo.service.MovimientosService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimiento")
@Controller
public class MovimientoController {

    @Autowired
    MovimientosService movimientosService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovimientoDto>> listarMovimeintos() throws Exception {
        List<MovimientoDto> lsMovimientos = movimientosService.listarMovimientos();
        if(lsMovimientos == null) {
            throw new Exception("NO HAY REGISTRO");
        }
        return new ResponseEntity<List<MovimientoDto>>(lsMovimientos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> crearMovimiento(@RequestBody MovimientoDto movimientoDto) {
        try {

            Response response= movimientosService.crearMovimiento(movimientoDto);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> actualizarMovimiento(@PathVariable("id") int id, @RequestBody MovimientoDto movimientoDto) {
        try {

            Response response= movimientosService.actualizarMovimiento(id, movimientoDto);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteMovimiento(@PathVariable("id") int id) {
        try {

            Response response= movimientosService.eliminarMovimiento(id);

            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
