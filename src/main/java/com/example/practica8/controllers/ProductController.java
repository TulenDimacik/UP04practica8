package com.example.practica8.controllers;

import com.example.practica8.models.Material;
import com.example.practica8.models.Product;
import com.example.practica8.models.ProductSize;
import com.example.practica8.models.ProductType;
import com.example.practica8.repo.MaterialRepository;
import com.example.practica8.repo.ProductRepository;
import com.example.practica8.repo.ProductSizeRepository;
import com.example.practica8.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public String productMain(Model model)
    {
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials );

        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes",productSizes );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes );

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "mainProduct";
    }

    @GetMapping("/product/add") //переходит на вью
    public String productAdd(@ModelAttribute("product") Product product, Model model)
    {
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials1",materials );

        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes1",productSizes );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes1",productTypes );

        return "productAdd";
    }

    @PostMapping("/product/add")// добавление в бд
    public String productAdd(@ModelAttribute("product") @Valid Product product,
                            BindingResult bindingResult,
                            @RequestParam(defaultValue = "") String materialName,
                            @RequestParam(defaultValue = "") String sizeName,
                            @RequestParam(defaultValue = "") String typeName,
                            Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Material> materials = materialRepository.findAll();
            model.addAttribute("materials1",materials );

            Iterable<ProductSize> productSizes = productSizeRepository.findAll();
            model.addAttribute("productSizes1",productSizes );

            Iterable<ProductType> productTypes = productTypeRepository.findAll();
            model.addAttribute("productTypes1",productTypes );
            return "productAdd";
        }
        product.setMaterial(materialRepository.findByMaterialName(materialName));
        product.setSize(productSizeRepository.findBySizeName(sizeName));
        product.setProductType(productTypeRepository.findByTypeName(typeName));
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping ("/product/{idProduct}/edit")
    public  String productEdit(@PathVariable("idProduct") long idProduct, Model model)
    {
        Product res = productRepository.findById(idProduct).orElseThrow();
        model.addAttribute("product1",res);

        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials );

        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes",productSizes );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes );

        return "productEdit";
    }

    @PostMapping ("/product/{idProduct}/edit")
    public  String productUpdate(@ModelAttribute("product") @Valid Product product1, BindingResult bindingResult,
                                 @PathVariable("idProduct")long idProduct,
                                 @RequestParam String materialName,
                                 @RequestParam String sizeName,
                                 @RequestParam String typeName,Model model)
    {
        if(bindingResult.hasErrors()) {

            Iterable<Material> materials = materialRepository.findAll();
            model.addAttribute("materials",materials );

            Iterable<ProductSize> productSizes = productSizeRepository.findAll();
            model.addAttribute("productSizes",productSizes );

            Iterable<ProductType> productTypes = productTypeRepository.findAll();
            model.addAttribute("productTypes",productTypes );
            return "productEdit";
        }
        product1.setMaterial(materialRepository.findByMaterialName(materialName));
        product1.setSize(productSizeRepository.findBySizeName(sizeName));
        product1.setProductType(productTypeRepository.findByTypeName(typeName));
        productRepository.save(product1);
        return "redirect:/product";
    }
    @PostMapping("/product/{idProduct}/remove")
    public String productDelete(@PathVariable("idProduct") long idProduct, Model model){
        Product product = productRepository.findById(idProduct).orElseThrow();
        productRepository.delete(product);
        return "redirect:/product";
    }

}
