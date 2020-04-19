package com.learntotestviajunit5.controllers;

import com.learntotestviajunit5.fauxspring.BindingResult;
import com.learntotestviajunit5.fauxspring.WebDataBinder;
import com.learntotestviajunit5.model.Pet;
import com.learntotestviajunit5.model.Visit;
import com.learntotestviajunit5.services.PetService;
import com.learntotestviajunit5.services.VisitService;
import java.util.Map;
import javax.validation.Valid;


public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public Visit loadPetWithVisit(Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    public String initNewVisitForm(Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
