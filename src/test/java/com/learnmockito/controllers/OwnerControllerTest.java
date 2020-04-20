package com.learnmockito.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.learntotestviajunit5.controllers.OwnerController;
import com.learntotestviajunit5.fauxspring.BindingResult;
import com.learntotestviajunit5.model.Owner;
import com.learntotestviajunit5.services.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
/**
Тестирование одного из контроллеров в классе OwnerController
 Пример использования ArgumentCaptor
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

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


  /*
  Тестируем контроллер processFindForm, использующий аргументы во входящем параметре
  findAllByLastNameLike("%"+ owner.getLastName() + "%")
   */
  @Test
  void processFindFormWildcardString() {
    //given
    Owner owner = new Owner(1l, "Joe", "Buck");
    List<Owner> ownerList = new ArrayList<>();
    final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class); // to capture argument values for further assertions.
    given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

    //when
    String viewName = controller.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());
  }

  /*
  Тестируем контроллер processFindForm, использующий аргументы во входящем параметре
  findAllByLastNameLike("%"+ owner.getLastName() + "%")
  Второй способ черезе аннотацию  @Captor

   */
  @Test
  void processFindFormWildcardStringAnnotation() {
    //given
    Owner owner = new Owner(1l, "Joe", "Buck");
    List<Owner> ownerList = new ArrayList<>();
    given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

    //when
    String viewName = controller.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
  }


  /*
  Проверка контроллера processFindForm
   */
  @Test
  void processCreationFormHasErrors() {
    //given
    Owner owner = new Owner(1l, "Jim", "Bob");
    given(bindingResult.hasErrors()).willReturn(true);

    //when
    String viewName = controller.processCreationForm(owner, bindingResult);

    //then
    assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
  }

  /*
Проверка контроллера
 */
  @Test
  void processCreationFormNoErrors() {
    //given
    Owner owner = new Owner(5l, "Jim", "Bob");
    given(bindingResult.hasErrors()).willReturn(false);
    given(ownerService.save(any())).willReturn(owner);

    //when
    String viewName = controller.processCreationForm(owner, bindingResult);

    //then
    assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
  }


}