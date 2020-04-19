package com.learntotestviajunit5.controllers;


import com.learntotestviajunit5.fauxspring.Model;
import com.learntotestviajunit5.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
