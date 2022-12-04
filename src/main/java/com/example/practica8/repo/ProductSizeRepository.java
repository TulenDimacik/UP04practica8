package com.example.practica8.repo;

import com.example.practica8.models.ProductSize;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeRepository extends CrudRepository<ProductSize,Long> {
    ProductSize findBySizeName(String sizeName);
}
