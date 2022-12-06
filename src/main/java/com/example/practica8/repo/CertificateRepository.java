package com.example.practica8.repo;

import com.example.practica8.models.Certificate;
import org.springframework.data.repository.CrudRepository;

public interface CertificateRepository extends CrudRepository<Certificate,Long> {

    Certificate findByCertificateName(String certificateName);
}
