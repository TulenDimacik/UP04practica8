package com.example.practica8.repo;

import com.example.practica8.models.Employee;
import com.example.practica8.models.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {

    Supplier findByCompanyName(String companyName);
}
