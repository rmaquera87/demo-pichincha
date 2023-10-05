package com.example.demo.service.impl;

import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.CuentaDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Persona;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.CuentaRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    CuentaRepository cuentaRepository;


    @Autowired
    public List<CuentaDto> listarCuenta(){

        List<Cuenta> lsCuentaEntity =cuentaRepository.findAll();
        List<CuentaDto> lsCuentaDto = new ArrayList<>();

        for (Cuenta c: lsCuentaEntity) {

            lsCuentaDto.add(CuentaDto.builder()
                    .idCuenta(c.getIdCuenta())
                    .numeroCuenta(c.getNumeroCuenta())
                    .tipoCuenta(c.getTipoCuenta())
                    .saldoInicial(c.getSaldoInicial())
                    .estado(c.isEstado())
                    .build()
            );
        }


        return lsCuentaDto;


    }

    public Response crearCuenta(CuentaDto cuentaDto){
        Response response=null;
        try {

            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
            cuenta.setEstado(true);
            Cliente cliente = clienteRepository.findById(cuentaDto.getIdCliente()).get();
            cuenta.setCliente(cliente);

            cuentaRepository.save(cuenta);

            response= Response.builder()
                    .estado(true)
                    .mensaje("Se guardó correctamente.")
                    .build();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());

            response= Response.builder()
                    .estado(false)
                    .mensaje("Error en la operación.")
                    .build();
        }
        return response;
    }

    public Response actualizarCuenta(int id, CuentaDto cuentaDto){
        Response response=null;
        try {

            Cuenta cuenta = cuentaRepository.findById(id).get();

            cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
            cuenta.setEstado(true);
            Cliente cliente = clienteRepository.findById(cuentaDto.getIdCliente()).get();
            cuenta.setCliente(cliente);

            cuentaRepository.save(cuenta);

            response = Response.builder()
                    .estado(true)
                    .mensaje("Se actualizó correctamente.")
                    .build();

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

            response= Response.builder()
                    .estado(false)
                    .mensaje("Error en la operación.")
                    .build();
        }
        return response;
    }

    public Response eliminarCuenta(int id){
        cuentaRepository.deleteById(id);

        return Response.builder()
                .estado(true)
                .mensaje("Se eliminó correctamente.")
                .build();
    }
}
