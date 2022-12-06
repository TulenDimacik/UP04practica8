package com.example.practica8.repo;

import com.example.practica8.models.Material;
import com.example.practica8.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository  extends CrudRepository<Product,Long> {
    Product findByProductName(String productName);
    Product findByIdProduct(Long idProduct);

    List<Product> findByProductNameContains (String productName);
}
