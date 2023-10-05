package com.example.demo.service.impl;

import com.example.demo.dto.MovimientoDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Movimientos;
import com.example.demo.repository.CuentaRepository;
import com.example.demo.repository.MovimientosRepository;
import com.example.demo.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovimientosServiceImpl implements MovimientosService {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    MovimientosRepository movimientosRepository;

    @Autowired
    public List<MovimientoDto> listarMovimientos(){

        List<Movimientos> lsMovimientosEntity =movimientosRepository.findAll();
        List<MovimientoDto> lsMovimientoDto = new ArrayList<>();

        for (Movimientos m: lsMovimientosEntity) {

            lsMovimientoDto.add(MovimientoDto.builder()
                    .fecha(m.getFecha())
                    .valor(m.getValor())
                    .saldo(m.getSaldo())
                    .build()
            );
        }


        return lsMovimientoDto;


    }

    public Response crearMovimiento(MovimientoDto movimientoDto){
        Response response=null;
        try {
            Double saldo=this.obtenerSaldo(movimientoDto.getIdCuenta());
            if(saldo<=0){
                return Response.builder()
                        .estado(false)
                        .mensaje("Saldo no disponible")
                        .build();
            }

            Movimientos movimientos= new Movimientos();
            movimientos.setFecha(new Date());
            movimientos.setValor(movimientoDto.getValor());
            movimientos.setSaldo(saldo+movimientoDto.getValor());
            Cuenta cuenta = cuentaRepository.findById(movimientoDto.getIdCuenta()).get();
            movimientos.setCuenta(cuenta);

            movimientosRepository.save(movimientos);

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

    public Response actualizarMovimiento(int id, MovimientoDto movimientoDto){
        Response response=null;
        try {

            Movimientos movimientos = movimientosRepository.findById(id).get();

            movimientos.setFecha(new Date());
            movimientos.setValor(movimientoDto.getValor());
            movimientos.setSaldo(0);
            Cuenta cuenta = cuentaRepository.findById(movimientoDto.getIdCuenta()).get();
            movimientos.setCuenta(cuenta);

            movimientosRepository.save(movimientos);

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

    public Response eliminarMovimiento(int id){
        movimientosRepository.deleteById(id);

        return Response.builder()
                .estado(true)
                .mensaje("Se eliminó correctamente.")
                .build();
    }

    public Double obtenerSaldo(int idCuenta){

        Cuenta cuenta = cuentaRepository.findById(idCuenta).get();

        Double saldoFinal= cuenta.getSaldoInicial();

        Movimientos movimientos= new Movimientos();
        movimientos.setCuenta(cuenta);

        List<Movimientos> lsMovimientosEntity =movimientosRepository.findAll();

        List<MovimientoDto> lsMovimientoDto = new ArrayList<>();

        for (Movimientos m: lsMovimientosEntity) {
                if(m.getCuenta().getIdCuenta()==idCuenta){
                    saldoFinal+=m.getValor();
                }

        }

        return saldoFinal;
    }
}
