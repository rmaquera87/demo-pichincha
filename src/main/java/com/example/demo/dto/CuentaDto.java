package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {

    private int idCuenta;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private int idCliente;

}
