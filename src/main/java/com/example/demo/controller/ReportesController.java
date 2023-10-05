package com.example.demo.controller;

import com.example.demo.dto.ReporteDto;
import com.example.demo.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
@Controller
public class ReportesController {

    @Autowired
    ReportesService reportesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReporteDto>> listarEstadoCuenta(@RequestParam(value = "idCliente") int idCliente, @RequestParam("fechaIni") String fechaIni, @RequestParam("fechaFin") String fechaFin) throws Exception {
        List<ReporteDto> lsEstadoCuenta= reportesService.listarEstadoCuenta(idCliente, fechaIni, fechaFin);

        if(lsEstadoCuenta == null) {
            throw new Exception("NO HAY REGISTRO");
        }
        return new ResponseEntity<List<ReporteDto>>(lsEstadoCuenta, HttpStatus.OK);
    }

}
