package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private int idCliente;
    private String nombres;
    private String genero;
    private int edad;
    private int identificacion;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private boolean estado;

}
