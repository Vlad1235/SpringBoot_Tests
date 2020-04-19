package com.learntotestviajunit5.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.ControllersTest;
import com.learntotestviajunit5.fauxspring.Model;
import com.learntotestviajunit5.model.Vet;
import com.learntotestviajunit5.services.SpecialtyService;
import com.learntotestviajunit5.services.VetService;
import com.learntotestviajunit5.services.map.SpecialityMapService;
import com.learntotestviajunit5.services.map.VetMapService;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class VetControllerTest implements ControllersTest {

  VetService vetService;
  SpecialtyService specialtyService;
  VetController vetController;

  @BeforeEach
  void setUp() {
    specialtyService = new SpecialityMapService();
    vetService = new VetMapService(specialtyService);
    vetController = new VetController(vetService);

    Vet vet1 = new Vet(1L, "joe", "buck", null);
    Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

    vetService.save(vet1);
    vetService.save(vet2);
  }

  @Disabled
  @Test
  void listVets() {
    Model model = new ModelMapImpl();
    String view = vetController.listVets(model);
    assertThat("vets/index").isEqualTo(view);
  }
}