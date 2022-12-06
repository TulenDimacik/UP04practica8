package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterial;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =15, message = "Размер должен быть от 2 до 15")
    private String materialName;
    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Collection<Product> products;
    public Long getIdMaterial() {return idMaterial;}

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
