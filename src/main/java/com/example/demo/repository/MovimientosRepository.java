package com.example.demo.repository;

import com.example.demo.dto.ReporteDto;
import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer> {
    @Query(value="select m.fecha,p.nombre as cliente,c.numero_cuenta,c.tipo_cuenta as tipo,c.saldo_inicial,c.estado, m.valor as movimiento, m.saldo as saldo_disponible \n" +
            "from movimientos m\n" +
            "inner join cuenta c on m.id_cuenta = c.id_cuenta\n" +
            "inner join cliente cl on c.id_cliente = cl.id_cliente\n" +
            "inner join persona p on cl.id_persona = p.id_persona\n" +
            "where cl.id_cliente = :idCliente\n"+
            "and date(m.fecha) between :fechaIni and :fechaFin"
            , nativeQuery=true)

    List<Object[]> getEstadoCuenta(int idCliente, String fechaIni, String fechaFin);
}
