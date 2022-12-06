package com.example.practica8.controllers;

import com.example.practica8.models.*;
import com.example.practica8.repo.EmployeeRepository;
import com.example.practica8.repo.InvoiceRepository;
import com.example.practica8.repo.ProductRepository;
import com.example.practica8.repo.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('ZAKUPMANAGER')")
@Controller
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/invoice")
    public String productMain(Model model)
    {
        Iterable<Invoice> invoices = invoiceRepository.findAll();
        model.addAttribute("invoices", invoices);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        Iterable<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "mainInvoice";
    }

    @GetMapping("/invoice/add") //переходит на вью
    public String productAdd(@ModelAttribute("invoice") Invoice invoice, Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        Iterable<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "invoiceAdd";
    }

    @PostMapping("/invoice/add")// добавление в бд
    public String productAdd(@ModelAttribute("invoice") Invoice invoice,
                             @RequestParam(defaultValue = "") String number,
                             @RequestParam(defaultValue = "") String productName,
                             @RequestParam(defaultValue = "") String warehouseAddress,
                             Model model)
    {
//        if(bindingResult.hasErrors()) {
//            Iterable<Material> materials = materialRepository.findAll();
//            model.addAttribute("materials1",materials );
//
//            Iterable<ProductSize> productSizes = productSizeRepository.findAll();
//            model.addAttribute("productSizes1",productSizes );
//
//            Iterable<ProductType> productTypes = productTypeRepository.findAll();
//            model.addAttribute("productTypes1",productTypes );
//            return "invoiceAdd";
//        }
        invoice.setEmployee(employeeRepository.findByNumber(number));
        invoice.setProduct(productRepository.findByProductName(productName));
        invoice.setWarehouse(warehouseRepository.findByWarehouseAddress(warehouseAddress));
        invoiceRepository.save(invoice);
        return "redirect:/invoice";
    }

    @GetMapping ("/invoice/{idInvoice}/edit")
    public  String invoiceEdit(@PathVariable("idInvoice") long idInvoice, Model model)
    {
        Invoice res = invoiceRepository.findById(idInvoice).orElseThrow();
        model.addAttribute("invoice",res);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        Iterable<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "invoiceEdit";
    }

    @PostMapping ("/invoice/{idInvoice}/edit")
    public  String productUpdate(@ModelAttribute("invoice") Invoice invoice,
                                 @PathVariable("idInvoice")long idInvoice,
                                 @RequestParam String productName,
                                 @RequestParam String warehouseAddress,
                                 @RequestParam String number,
                                 Model model)
    {
        invoice.setProduct(productRepository.findByProductName(productName));
        invoice.setWarehouse(warehouseRepository.findByWarehouseAddress(warehouseAddress));
        invoice.setEmployee(employeeRepository.findByNumber(number));
        invoiceRepository.save(invoice);
        return "redirect:/invoice";
    }
    @GetMapping("/invoice/{idInvoice}/remove")
    public String invoiceDelete(@PathVariable("idInvoice") long idInvoice, Model model){
        Invoice invoice = invoiceRepository.findById(idInvoice).orElseThrow();
        invoiceRepository.delete(invoice);
        return "redirect:/invoice";
    }
}
