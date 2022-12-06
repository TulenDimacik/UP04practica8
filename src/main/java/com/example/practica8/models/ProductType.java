package com.example.practica8.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "productType")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductType;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1,max =15, message = "Вид товара должен быть от 1 до 15")
    private String typeName;
    @OneToMany(mappedBy = "productType", fetch = FetchType.EAGER ,  cascade = CascadeType.ALL)
    private Collection<Product> products;



    public Long getIdProductType() {return idProductType;}

    public void setIdProductType(Long idProductType) {this.idProductType = idProductType;}

    public String getTypeName() {return typeName;}

    public void setTypeName(String typeName) {this.typeName = typeName;}

    public Collection<Product> getProducts() {return products;}

    public void setProducts(Collection<Product> products) {this.products = products;}
}
