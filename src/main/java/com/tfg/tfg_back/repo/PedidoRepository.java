package com.tfg.tfg_back.repo;

import com.tfg.tfg_back.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
