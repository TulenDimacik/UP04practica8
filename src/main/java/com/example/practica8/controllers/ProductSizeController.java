package com.example.practica8.controllers;

import com.example.practica8.models.ProductSize;
import com.example.practica8.repo.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductSizeController {
    @Autowired
    ProductSizeRepository productSizeRepository;

    @GetMapping("/product/size")
    public String mainProductSize(Model model){
        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes",productSizes);
        return "mainProductSize";
    }

    @GetMapping("/product/size/add") //переходит на вью
    public String productSizeAdd(@ModelAttribute("productSize") ProductSize productSize, Model model)
    {
        return "productSizeAdd";
    }

    @PostMapping("/product/size/add")
    public String productSizeAdd(@ModelAttribute("productSize") @Valid  ProductSize productSize,
                              BindingResult bindingResult,
                              Model model)
    {
        if(bindingResult.hasErrors()) {
            return "productSizeAdd";
        }
        productSizeRepository.save(productSize);
        return "redirect:/product/size";
    }

    @PostMapping("/product/size/{idSize}/remove")
    public String productSizeDelete(@PathVariable("idSize") long idSize, Model model){
        ProductSize productSize = productSizeRepository.findById(idSize).orElseThrow();
        productSizeRepository.delete(productSize);
        return "redirect:/product/size";
    }

}
