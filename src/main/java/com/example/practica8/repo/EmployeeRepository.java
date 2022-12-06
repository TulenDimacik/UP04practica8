package com.example.practica8.repo;

import com.example.practica8.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee findByNumber(String number);

    List<Employee> findByNumberEquals(String number);
}
