package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =50, message = "Размер должен быть от 2 до 50")
    private String companyName;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 5,max =60, message = "Размер должен быть от 5 до 60")
    private String legalAddress;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Collection<Contract> contracts;

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }
}
