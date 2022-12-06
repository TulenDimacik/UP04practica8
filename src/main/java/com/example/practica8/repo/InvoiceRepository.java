package com.example.practica8.repo;

import com.example.practica8.models.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {

}
