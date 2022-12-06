package com.example.practica8.controllers;

import com.example.practica8.models.ProductSize;
import com.example.practica8.models.ProductType;
import com.example.practica8.repo.ProductSizeRepository;
import com.example.practica8.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('SELLMANAGER')")
@Controller
public class ProductTypeController {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @GetMapping("/product/type")
    public String mainProductType(Model model){
        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes);
        return "mainProductType";
    }

    @GetMapping("/product/type/add") //переходит на вью
    public String productTypeAdd(@ModelAttribute("productType") ProductType productType, Model model)
    {
        return "productTypeAdd";
    }

    @PostMapping("/product/type/add")
    public String productTypeAdd(@ModelAttribute("productType") @Valid ProductType productType,
                                 BindingResult bindingResult,
                                 Model model)
    {
        if(bindingResult.hasErrors()) {
            return "productTypeAdd";
        }
        productTypeRepository.save(productType);
        return "redirect:/product/type";
    }

    @GetMapping("/product/type/{idProductType}/remove")
    public String productTypeDelete(@PathVariable("idProductType") long idProductType, Model model){
        ProductType productType = productTypeRepository.findById(idProductType).orElseThrow();
        productTypeRepository.delete(productType);
        return "redirect:/product/type";
    }
}
