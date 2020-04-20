package com.learnmockito.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

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
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Способ проверять что мок не был вызван.
 * Например,дойдя до середины теста, понимаем, что более нам этот мок не потребуется
 */
@ExtendWith(MockitoExtension.class)
class OwnerController4Test {

  private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
  private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

  @Mock
  OwnerService ownerService;

  @Mock
  Model model;

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
  void processFindFormWildcardNotFound() {
    //given
    Owner owner = new Owner(1l, "Joe", "DontFindMe");

    //when
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verifyNoMoreInteractions(ownerService);

    //then
    assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    verifyZeroInteractions(model);
  }

}