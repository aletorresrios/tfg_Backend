package com.tfg.tfg_back.repo;


import com.tfg.tfg_back.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
