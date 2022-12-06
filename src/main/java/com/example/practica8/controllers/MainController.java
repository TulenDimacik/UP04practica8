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

    @Autowired
    CartRepository cartRepository;

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
    @GetMapping ("/main/{id}/{idProduct}/add")
    public  String cartEdit(@PathVariable("id") Long id,
                            @PathVariable("idProduct") Long idProduct, Model model)
    {
        Product res = productRepository.findById(idProduct).orElseThrow();
        model.addAttribute("product",res);

        User res1 = userRepository.findById(id).orElseThrow();
        model.addAttribute("user",res1);


        return "cartAdd";
    }


    @PostMapping("/main/{id}/{idProduct}/add")// добавление в бд
    public String cartAdd(@ModelAttribute("cart") Cart cart, @PathVariable("id") Long id,
                          @PathVariable("idProduct") Long idProduct,
                             Model model)
    {
        cart.setProduct(productRepository.findByIdProduct(idProduct));
        cart.setUser(userRepository.findUserById(id));
        cartRepository.save(cart);
        return "redirect:/main";
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
