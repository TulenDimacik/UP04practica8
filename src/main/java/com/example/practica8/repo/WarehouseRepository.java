package com.example.practica8.repo;

import com.example.practica8.models.Material;
import com.example.practica8.models.Warehouse;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    Warehouse findByWarehouseAddress(String warehouseAddress);
}
