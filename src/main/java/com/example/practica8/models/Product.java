package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =50,message = "Размер должен быть от 2 до 50")
    private String productName;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0")
    @Max(value = 1000000,message = "Значение не должно быть больше 1млн.")
    private float price;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ProductSize productSize;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ProductType productType;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Material material;

    public Long getIdProduct() {return idProduct;}

    public void setIdProduct(Long idProduct) {this.idProduct = idProduct;}

    public String getProductName() {return productName;}

    public void setProductName(String productName) {this.productName = productName;}

    public float getPrice() {return price;}

    public void setPrice(float price) {this.price = price;}

    public ProductSize getSize() {return productSize;}

    public void setSize(ProductSize productSize) {this.productSize = productSize;}

    public ProductType getProductType() {return productType;}

    public void setProductType(ProductType productType) {this.productType = productType;}

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}