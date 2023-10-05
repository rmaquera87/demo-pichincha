package com.example.demo.service;


import com.example.demo.dto.MovimientoDto;
import com.example.demo.dto.Response;

import java.util.List;

public interface MovimientosService {
    public List<MovimientoDto> listarMovimientos();
    public Response crearMovimiento(MovimientoDto movimientoDto);
    public Response actualizarMovimiento(int id, MovimientoDto movimientoDto);
    public Response eliminarMovimiento(int id);
}
