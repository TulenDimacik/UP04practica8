package com.example.practica8.controllers;

import com.example.practica8.models.*;
import com.example.practica8.repo.EmployeeRepository;
import com.example.practica8.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/employee")
    public String productMain(Model model)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "mainEmployee";
    }

    @GetMapping("/employee/add") //переходит на вью
    public String employeeAdd(@ModelAttribute("employee") Employee employee, Model model)
    {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "addEmployee";
    }
    @PostMapping("/employee/add")// добавление в бд
    public String employeeAdd(@ModelAttribute("employee") @Valid Employee employee,
                              BindingResult bindingResult,
                              @RequestParam String username,
                              Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("users",users);
            return "addEmployee";
        }
        employee.setUser(userRepository.findUserByUsername(username));
        employeeRepository.save(employee);
        return "redirect:/product";
    }
    @GetMapping("/employee/{id}/remove")
    public String productDelete(@PathVariable("id") long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/product";
    }

    @GetMapping("/employee/filter")
    public String employeeFilter(Model model)
    {
        return "employeeFilter";
    }
    @PostMapping("/employee/filter/result")
    public String employeeResult(@RequestParam String number, Model model)
    {
        List<Employee> result = employeeRepository.findByNumberEquals(number);
        model.addAttribute("result", result);
        return "employeeFilter";
    }
}
