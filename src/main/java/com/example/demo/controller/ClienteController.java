package com.example.demo.controller;

import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.Response;
import com.example.demo.service.ClienteService;
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
@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @ResponseBody
    @GetMapping(value = "test")
    public String test(){
        return "ok";
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDto>> listarCliente() throws Exception {
        List<ClienteDto> lsCliente = clienteService.getCliente();
        if(lsCliente == null) {
            throw new Exception("NO HAY REGISTRO");
        }
        return new ResponseEntity<List<ClienteDto>>(lsCliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> crearCliente(@RequestBody ClienteDto cliente) {
        try {

            Response response=clienteService.crearCliente(cliente);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> actualizarCliente(@PathVariable("id") int id, @RequestBody ClienteDto cliente) {
        try {

            Response response=clienteService.actualizarCliente(id, cliente);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCliente(@PathVariable("id") int id) {
        try {

            Response response= clienteService.eliminarCliente(id);

            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
