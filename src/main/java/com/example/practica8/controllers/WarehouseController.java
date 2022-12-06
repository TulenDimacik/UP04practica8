package com.example.practica8.controllers;

import com.example.practica8.models.Material;
import com.example.practica8.models.Warehouse;
import com.example.practica8.repo.MaterialRepository;
import com.example.practica8.repo.WarehouseRepository;
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

@PreAuthorize("hasAnyAuthority('ZAKUPMANAGER')")
@Controller
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping("/warehouse")
    public String product(Model model){
        Iterable<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses",warehouses);
        return "mainWarehouse";
    }

    @GetMapping("/warehouse/add") //переходит на вью
    public String matAdd(@ModelAttribute("warehouse") Warehouse warehouse, Model model)
    {
        return "warehouseAdd";
    }

    @PostMapping("/warehouse/add")
    public String materialAdd(@ModelAttribute("warehouse") @Valid Warehouse warehouse,
                              BindingResult bindingResult,
                              Model model)
    {
        if(bindingResult.hasErrors()) {
            return "warehouseAdd";
        }
        warehouseRepository.save(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/warehouse/{idWarehouse}/remove")
    public String materialDelete(@PathVariable("idWarehouse") long idWarehouse, Model model){
        Warehouse warehouse = warehouseRepository.findById(idWarehouse).orElseThrow();
        warehouseRepository.delete(warehouse);
        return "redirect:/warehouse";
    }
}
