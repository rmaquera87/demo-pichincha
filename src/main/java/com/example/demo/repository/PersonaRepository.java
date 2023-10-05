package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository  extends JpaRepository<Persona, Integer> {
}
