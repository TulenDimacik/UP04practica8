package com.example.practica8.controllers;

import com.example.practica8.models.*;
import com.example.practica8.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/main")
    public String productMain(Model model)
    {
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials );

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users );

        Iterable<ProductSize> productSizes = productSizeRepository.findAll();
        model.addAttribute("productSizes",productSizes );

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        model.addAttribute("productTypes",productTypes );

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "mainPage";
    }




//
//    @GetMapping("/client/{id_client}/{id_product}/remove")
//    public  String clientProductDelete(@PathVariable(value = "id_client") long id_client,
//                                       @PathVariable(value = "id_product") Long id_product,
//                                       Model model)
//    {
//        ClientInformation client = clientRepository.findById(id_client).orElseThrow();
//        Product product = productRepository.findById(id_product).orElseThrow();
//        client.getProducts().remove(product);
//        clientRepository.save(client);
//        return "redirect:/";
//    }

}
