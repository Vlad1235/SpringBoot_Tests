package com.learnmockito.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Способ проверять в каком порядке были вызваны моки
 * Использование класса InOrder
 */
@ExtendWith(MockitoExtension.class)
class OwnerController3Test {

  private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
  private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController controller;

  @Mock
  Model model;

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
    InOrder order = inOrder(ownerService, model); // перечисляем, порядок вызова каких моков хотим проверить. В любом порядке тут можно указать

    //when
    String viewName = controller.processFindForm(owner, bindingResult, model); // 2 мока используются

    //then
    assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

    /*
    порядок вызова важен.
    если местами поменяем выпадет в ошибку
     */
    order.verify(ownerService).findAllByLastNameLike(anyString());
    order.verify(model).addAttribute(anyString(),anyList());
  }

}