package com.example.demo.service.impl;

import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Persona;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    public List<ClienteDto> getCliente(){

        List<Cliente> lsClienteEntity =clienteRepository.findAll();
        List<ClienteDto> lsClienteDto = new ArrayList<>();

        for (Cliente c: lsClienteEntity) {

            lsClienteDto.add(ClienteDto.builder()
                    .idCliente(c.getIdCliente())
                    .nombres(c.getPersona().getNombre())
                    .genero(c.getPersona().getGenero())
                    .edad(c.getPersona().getEdad())
                    .identificacion(c.getPersona().getIdentificacion())
                    .direccion(c.getPersona().getDireccion())
                    .telefono(c.getPersona().getTelefono())
                    .contrasenia(c.getContrasenia())
                    .estado(c.isEstado())
                    .build()
            );
        }


        return lsClienteDto;


    }

    public Response crearCliente(ClienteDto clienteDto){
        Response response=null;
        try {

            Persona persona = new Persona();
            persona.setNombre(clienteDto.getNombres());
            persona.setGenero(clienteDto.getGenero());
            persona.setEdad(clienteDto.getEdad());
            persona.setIdentificacion(clienteDto.getIdentificacion());
            persona.setDireccion(clienteDto.getDireccion());
            persona.setTelefono(clienteDto.getTelefono());

            Cliente cliente = new Cliente();
            cliente.setContrasenia(clienteDto.getContrasenia());
            cliente.setEstado(clienteDto.isEstado());
            cliente.setPersona(persona);

            List<Cliente> lsCliente= new ArrayList<>();
            lsCliente.add(cliente);
            persona.setClientes(lsCliente);


            personaRepository.save(persona);
            clienteRepository.save(cliente);

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

    public Response actualizarCliente(int id, ClienteDto clienteDto){
        Response response=null;
        try {

            Cliente cliente = clienteRepository.findById(id).get();

            Persona persona = cliente.getPersona();
            persona.setNombre(clienteDto.getNombres());
            persona.setGenero(clienteDto.getGenero());
            persona.setEdad(clienteDto.getEdad());
            persona.setIdentificacion(clienteDto.getIdentificacion());
            persona.setDireccion(clienteDto.getDireccion());
            persona.setTelefono(clienteDto.getTelefono());

            cliente.setContrasenia(clienteDto.getContrasenia());
            cliente.setEstado(clienteDto.isEstado());
            cliente.setPersona(persona);

            clienteRepository.save(cliente);

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

    public Response eliminarCliente(int id){
        clienteRepository.deleteById(id);

        return Response.builder()
                .estado(true)
                .mensaje("Se eliminó correctamente.")
                .build();
    }
}
