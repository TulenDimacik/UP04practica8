package com.example.practica8.controllers;

import com.example.practica8.models.Material;
import com.example.practica8.repo.MaterialRepository;
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
public class MaterialController {
    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping("/material")
    public String product(Model model){
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials);
        return "mainMaterial";
    }

    @GetMapping("/material/add") //переходит на вью
    public String matAdd(@ModelAttribute("material") Material material, Model model)
    {
        return "materialAdd";
    }

    @PostMapping("/material/add")
    public String materialAdd(@ModelAttribute("material") @Valid Material material,
                              BindingResult bindingResult,
                              Model model)
    {
        if(bindingResult.hasErrors()) {
            return "materialAdd";
        }
        materialRepository.save(material);
        return "redirect:/material";
    }

    @PostMapping("/material/{idMaterial}/remove")
    public String materialDelete(@PathVariable("idMaterial") long idMaterial, Model model){
        Material material = materialRepository.findById(idMaterial).orElseThrow();
        materialRepository.delete(material);
        return "redirect:/material";
    }

}
