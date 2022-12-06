package com.example.practica8.controllers;

import com.example.practica8.models.*;
import com.example.practica8.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasAnyAuthority('SELLMANAGER')")
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

    @Autowired
    CertificateRepository certificateRepository;

    @GetMapping("/product")
    public String productMain(Model model)
    {
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials );

        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes",productSizes );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes );

        Iterable<Certificate> certificates = certificateRepository.findAll();
        model.addAttribute("certificates",certificates );

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

        Iterable<Certificate> certificates = certificateRepository.findAll();
        model.addAttribute("certificates",certificates );

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
                             @RequestParam(defaultValue = "") String certificateName,
                            Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Material> materials = materialRepository.findAll();
            model.addAttribute("materials1",materials );

            Iterable<ProductSize> productSizes = productSizeRepository.findAll();
            model.addAttribute("productSizes1",productSizes );

            Iterable<Certificate> certificates = certificateRepository.findAll();
            model.addAttribute("certificates",certificates );

            Iterable<ProductType> productTypes = productTypeRepository.findAll();
            model.addAttribute("productTypes1",productTypes );
            return "productAdd";
        }

        product.setMaterial(materialRepository.findByMaterialName(materialName));
        product.setSize(productSizeRepository.findBySizeName(sizeName));
        product.setProductType(productTypeRepository.findByTypeName(typeName));
        product.setCertificate(certificateRepository.findByCertificateName(certificateName));
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
        Iterable<Certificate> certificates = certificateRepository.findAll();
        model.addAttribute("certificates",certificates );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes );

        return "productEdit";
    }

    @GetMapping("/product/filter")
    public String productFilter(Model model)
    {
        return "productFilter";
    }
    @PostMapping("/product/filter/result")
    public String productResult(@RequestParam String productName, Model model)
    {
        List<Product> result = productRepository.findByProductNameContains(productName);
        model.addAttribute("result", result);
        return "productFilter";
    }
    @PostMapping ("/product/{idProduct}/edit")
    public  String productUpdate(@ModelAttribute("product") @Valid Product product1, BindingResult bindingResult,
                                 @PathVariable("idProduct")long idProduct,
                                 @RequestParam String materialName,
                                 @RequestParam String sizeName,
                                 @RequestParam String typeName,
                                 @RequestParam(defaultValue = "") String certificateName,
                                 Model model)
    {
        if(bindingResult.hasErrors()) {

            Iterable<Material> materials = materialRepository.findAll();
            model.addAttribute("materials",materials );

            Iterable<ProductSize> productSizes = productSizeRepository.findAll();
            model.addAttribute("productSizes",productSizes );

            Iterable<ProductType> productTypes = productTypeRepository.findAll();
            model.addAttribute("productTypes",productTypes );
            Iterable<Certificate> certificates = certificateRepository.findAll();
            model.addAttribute("certificates",certificates );

            return "productEdit";
        }
        product1.setCertificate(certificateRepository.findByCertificateName(certificateName));
        product1.setMaterial(materialRepository.findByMaterialName(materialName));
        product1.setSize(productSizeRepository.findBySizeName(sizeName));
        product1.setProductType(productTypeRepository.findByTypeName(typeName));
        productRepository.save(product1);
        return "redirect:/product";
    }
    @GetMapping("/product/{idProduct}/remove")
    public String productDelete(@PathVariable("idProduct") long idProduct, Model model){
        Product product = productRepository.findById(idProduct).orElseThrow();
        productRepository.delete(product);
        return "redirect:/product";
    }

}
