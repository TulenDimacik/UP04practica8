package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "productSize")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSize;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1,max =15, message = "Размер должен быть от 1 до 15")
    private String sizeName;

    @OneToMany(mappedBy = "productSize", fetch = FetchType.EAGER)
    private Collection<Product> products;

    public Long getIdSize() {return idSize;}

    public void setIdSize(Long idSize) {this.idSize = idSize;}
    public String getSizeName() {return sizeName;}

    public void setSizeName(String sizeName) {this.sizeName = sizeName;}

    public Collection<Product> getProducts() {return products;}

    public void setProducts(Collection<Product> products) {this.products = products;}
}
