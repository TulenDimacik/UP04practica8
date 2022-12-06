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

@PreAuthorize("hasAnyAuthority('ZAKUPMANAGER')")
@Controller
public class ContractController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SupplierRepository supplierRepository;


    @GetMapping("/contract")
    public String contractMain(Model model)
    {
        Iterable<Contract> contracts = contractRepository.findAll();
        model.addAttribute("contracts",contracts);

        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers",suppliers);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees",employees);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "MainContract";
    }

    @GetMapping("/contract/add") //переходит на вью
    public String contractAdd(@ModelAttribute("contract") Contract contract, Model model)
    {
        Iterable<Contract> contracts = contractRepository.findAll();
        model.addAttribute("contracts",contracts);

        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers",suppliers);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees",employees);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "contractADD";
    }

    @PostMapping("/contract/add")// добавление в бд
    public String contractAdd(@ModelAttribute("contract") @Valid Contract contract,
                             BindingResult bindingResult,
                             @RequestParam(defaultValue = "") String companyName,
                             @RequestParam(defaultValue = "") String number,
                             @RequestParam(defaultValue = "") String productName,
                             Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("suppliers",suppliers);

            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees",employees);

            Iterable<Product> products = productRepository.findAll();
            model.addAttribute("products", products);
            return "contractADD";
        }
        contract.setSupplier(supplierRepository.findByCompanyName(companyName));
        contract.setEmployee(employeeRepository.findByNumber(number));
        contract.setProduct(productRepository.findByProductName(productName));
        contractRepository.save(contract);
        return "redirect:/contract";
    }

    @GetMapping ("/contract/{idContract}/edit")
    public  String contractEdit(@PathVariable("idContract") long idContract, Model model)
    {
        Contract res = contractRepository.findById(idContract).orElseThrow();
        model.addAttribute("contract",res);

        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers",suppliers);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees",employees);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "contractEdit";
    }

    @PostMapping ("/contract/{idContract}/edit")
    public  String contractUpdate(@ModelAttribute("contract") @Valid Contract contract, BindingResult bindingResult,
                                 @PathVariable("idContract")long idContract,
                                  @RequestParam(defaultValue = "") String companyName,
                                  @RequestParam(defaultValue = "") String number,
                                  @RequestParam(defaultValue = "") String productName,
                                  Model model)
    {
        if(bindingResult.hasErrors()) {

            Iterable<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("suppliers",suppliers);

            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees",employees);

            Iterable<Product> products = productRepository.findAll();
            model.addAttribute("products", products);
            return "contractEdit";
        }
        contract.setSupplier(supplierRepository.findByCompanyName(companyName));
        contract.setEmployee(employeeRepository.findByNumber(number));
        contract.setProduct(productRepository.findByProductName(productName));
        contractRepository.save(contract);
        return "redirect:/contract";
    }

    @GetMapping("/contract/{idContract}/remove")
    public String contractDelete(@PathVariable("idContract") long idContract, Model model){
        Contract contract = contractRepository.findById(idContract).orElseThrow();
        contractRepository.delete(contract);
        return "redirect:/contract";
    }
}
