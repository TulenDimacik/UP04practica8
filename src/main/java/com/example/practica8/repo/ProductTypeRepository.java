package com.example.practica8.repo;

import com.example.practica8.models.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends CrudRepository<ProductType,Long> {
    ProductType findByTypeName(String typeName);
}
