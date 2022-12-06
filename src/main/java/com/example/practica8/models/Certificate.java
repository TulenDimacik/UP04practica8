package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificate;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =30, message = "Размер должен быть от 2 до 30")
    private String certificateName;

    @NotNull(message = "Поле не может быть пустым")
    private Date issueDate;

    @OneToMany(mappedBy = "certificate", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Collection<Product> products;


    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Long getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(Long idCertificate) {
        this.idCertificate = idCertificate;
    }
}
