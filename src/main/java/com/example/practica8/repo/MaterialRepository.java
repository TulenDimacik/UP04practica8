package com.example.practica8.repo;

import com.example.practica8.models.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<Material,Long> {

        Material findByMaterialName(String material);
        }