package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWarehouse;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5, max = 60, message = "Размер должен быть от 5 до 60")
    private String warehouseAddress;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Invoice> invoices;

    public Long getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(Long idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }


    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }
}