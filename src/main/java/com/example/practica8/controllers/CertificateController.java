package com.example.practica8.controllers;

import com.example.practica8.models.Certificate;
import com.example.practica8.repo.CertificateRepository;
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
public class CertificateController {

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/certificate")
    public String certificate(Model model){
        Iterable<Certificate> certificates = certificateRepository.findAll();
        model.addAttribute("certificates",certificates);
        return "mainCertificate";
    }

    @GetMapping("/certificate/add")
    public String cerAdd(@ModelAttribute("certificate") Certificate certificate, Model model)
    {
        return "certificateAdd";
    }

    @PostMapping("/certificate/add")
    public String certificateAdd(@ModelAttribute("certificate1") @Valid Certificate certificate,
                                 BindingResult bindingResult,
                                 Model model)
    {
        if(bindingResult.hasErrors()) {
            return "certificateAdd";
        }
        certificateRepository.save(certificate);
        return "redirect:/certificate";
    }

    @GetMapping("/certificate/{idCertificate}/remove")
    public String certificateDelete(@PathVariable("idCertificate") long idCertificate, Model model){
        Certificate certificate = certificateRepository.findById(idCertificate).orElseThrow();
        certificateRepository.delete(certificate);
        return "redirect:/certificate";
    }
}
