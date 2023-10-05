package com.example.demo.service;


import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<ClienteDto> getCliente();
    public Response crearCliente(ClienteDto clienteDto);
    public Response actualizarCliente(int id, ClienteDto clienteDto);
    public Response eliminarCliente(int id);
}
