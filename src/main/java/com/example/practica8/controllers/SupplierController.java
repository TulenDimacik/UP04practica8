package com.example.practica8.controllers;

import com.example.practica8.models.*;
import com.example.practica8.repo.MaterialRepository;
import com.example.practica8.repo.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('ZAKUPMANAGER')")
@Controller
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/supplier")
    public String supplier(Model model){
        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers",suppliers);
        return "mainSupplier";
    }

    @GetMapping("/supplier/add") //переходит на вью
    public String supAdd(@ModelAttribute("supplier") Supplier supplier, Model model)
    {
        return "supplierAdd";
    }

    @PostMapping("/supplier/add")
    public String supplierAdd(@ModelAttribute("supplier") @Valid Supplier supplier,
                              BindingResult bindingResult,
                              Model model)
    {
        if(bindingResult.hasErrors()) {
            return "supplierAdd";
        }
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping ("/supplier/{idSupplier}/edit")
    public  String supplierEdit(@PathVariable("idSupplier") long idSupplier, Model model)
    {
        Supplier res = supplierRepository.findById(idSupplier).orElseThrow();
        model.addAttribute("supplier",res);
        return "supplierEdit";
    }

    @PostMapping ("/supplier/{idSupplier}/edit")
    public  String supplierUpdate(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult bindingResult,
                                 @PathVariable("idSupplier")long idSupplier,
                                 Model model)
    {
        if(bindingResult.hasErrors()) {

            return "supplierEdit";
        }
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/supplier/{idSupplier}/remove")
    public String supplierDelete(@PathVariable("idSupplier") long idSupplier, Model model){
        Supplier supplier= supplierRepository.findById(idSupplier).orElseThrow();
        supplierRepository.delete(supplier);
        return "redirect:/supplier";
    }


}
