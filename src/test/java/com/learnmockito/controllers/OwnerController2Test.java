package com.learnmockito.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.learntotestviajunit5.controllers.OwnerController;
import com.learntotestviajunit5.fauxspring.BindingResult;
import com.learntotestviajunit5.fauxspring.Model;
import com.learntotestviajunit5.model.Owner;
import com.learntotestviajunit5.services.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Более красивое оформление кода теста для контроллера processFindForm в классе OwnerController
 */
@ExtendWith(MockitoExtension.class)
class OwnerController2Test {

  private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
  private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController controller;

  @Mock
  BindingResult bindingResult;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @BeforeEach
  void setUp() {
    given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
        .willAnswer(invocation -> {
          List<Owner> owners = new ArrayList<>();

          String name = invocation.getArgument(0);

          if (name.equals("%Buck%")) {
            owners.add(new Owner(1l, "Joe", "Buck"));
            return owners;
          } else if (name.equals("%DontFindMe%")) {
            return owners;
          } else if (name.equals("%FindMe%")) {
            owners.add(new Owner(1l, "Joe", "Buck"));
            owners.add(new Owner(2l, "Joe2", "Buck2"));
            return owners;
          }
          throw new RuntimeException("Invalid Argument");
        });
  }

  @Test
  void processFindFormWildcardFound() {
    //given
    Owner owner = new Owner(1l, "Joe", "FindMe");

    //when
    String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

    //then
    assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
  }

  @Test
  void processFindFormWildcardStringAnnotation() {
    //given
    Owner owner = new Owner(1l, "Joe", "Buck");

    //when
    String viewName = controller.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
  }


  @Test
  void processFindFormWildcardNotFound() {
    //given
    Owner owner = new Owner(1l, "Joe", "DontFindMe");

    //when
    String viewName = controller.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
  }

}