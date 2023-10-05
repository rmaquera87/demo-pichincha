package com.example.demo.service.impl;

import com.example.demo.dto.ReporteDto;
import com.example.demo.repository.MovimientosRepository;
import com.example.demo.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportesServiceImpl implements ReportesService {

    @Autowired
    MovimientosRepository movimientosRepository;

    @Override
    public List<ReporteDto> listarEstadoCuenta(int idCliente, String fechaIni, String fechaFin){

        List<Object[]> lsDataReporte =movimientosRepository.getEstadoCuenta(idCliente, fechaIni, fechaFin);
        List<ReporteDto> lsReporte= new ArrayList<>();

        for (Object[] obj: lsDataReporte) {

            lsReporte.add(ReporteDto.builder()
                    .fecha((Date) obj[0])
                    .cliente((String) obj[1])
                    .numeroCuenta((String) obj[2])
                    .tipo((String) obj[3])
                    .saldoInicial(((BigDecimal) obj[4]).doubleValue())
                    .estado((boolean) obj[5])
                    .movimiento(((BigDecimal) obj[6]).doubleValue())
                    .saldoDisponible(((BigDecimal) obj[7]).doubleValue())
                    .build());
        }

        return lsReporte;

    }

}
