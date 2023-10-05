package com.example.demo.service;


import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.CuentaDto;
import com.example.demo.dto.Response;

import java.util.List;

public interface CuentaService {
    public List<CuentaDto> listarCuenta();
    public Response crearCuenta(CuentaDto cuentaDto);
    public Response actualizarCuenta(int id, CuentaDto cuentaDto);
    public Response eliminarCuenta(int id);
}
