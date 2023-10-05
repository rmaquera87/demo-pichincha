package com.example.demo.service;

import com.example.demo.dto.ReporteDto;


import java.util.List;

public interface ReportesService {
    public List<ReporteDto> listarEstadoCuenta(int idCliente, String fechaIni, String fechaFin);
}
