package com.learnmockito.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learntotestviajunit5.model.Speciality;
import com.learntotestviajunit5.repositories.SpecialtyRepository;
import com.learntotestviajunit5.services.springdatajpa.SpecialitySDJpaService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock
  SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService service;

  /*
  Standard TDD
   */
  @Test
  void deleteById() {
    service.deleteById(1L);
    service.deleteById(1L);
//    verify(specialtyRepository).deleteById(1l); // подтверждаем что мок был использован 1 раз. По умолчанию у метода verity() стоит проверка на 1 вызов
    verify(specialtyRepository, times(2)).deleteById(1l);
  }

  /*
  Implement BDD
   */
  @Test
  void deleteByIdBDD() {
    //given

    //when
    service.deleteById(1L);
    //then
    then(specialtyRepository).should(times(1)).deleteById(anyLong());
  }

  /*
  Standard TDD
   */
  @Test
  void deleteByIdAtLeast() {
    service.deleteById(1L);
    service.deleteById(1L);
    verify(specialtyRepository, atLeastOnce()).deleteById(1l); // хотя бы раз вызван мок
  }

  /*
Implement BDD
 */
  @Test
  void deleteByIdAtLeastBDD() {
    //given

    //when
    service.deleteById(1L);
    //then
    then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
  }

  @Test
  void deleteByIdAtMost() {
    service.deleteById(1L);
    service.deleteById(1L);
    service.deleteById(1L);
    service.deleteById(1L);
    verify(specialtyRepository, atMost(5)).deleteById(1l); // разрешено максимум 5 раз
  }

  @Test
  void deleteByIdNever() {
    service.deleteById(1L);
    verify(specialtyRepository, atLeastOnce()).deleteById(1l); // хотя бы раз
    verify(specialtyRepository, never()).deleteById(5L); // ни разу при указанном значении 5L
  }

  /*
Implement BDD
*/
  @Test
  void deleteByIdNeverBDD() {
    //given

    //when
    service.deleteById(1L);
    //then
    then(specialtyRepository).should(never()).deleteById(5L);
  }

  /*
Standard TDD
   */
  @Test
  void findById() {
    Speciality speciality = new Speciality();
    when(specialtyRepository.findById(1L)).thenReturn(Optional
        .of(speciality)); // указываем, что вернуть. Важно 1L передаем! Если передать другое обучение не сработает Strict stubbing argument mismatch ошибка
    Speciality foundSpeciality = service.findById(1l);
    assertThat(foundSpeciality).isNotNull(); // AssertJ
    verify(specialtyRepository, times(1)).findById(anyLong()); // подтверждаем, что метод findById() был вызван 1 раз с переменной типа Long
  }

  /*
Standard TDD
   */
  @Test
  void findByIdBDD() {
    //given
    Speciality speciality = new Speciality();
    given(specialtyRepository.findById(anyLong())).willReturn(Optional.of(speciality));
    //when
    Speciality foundSpeciality = service.findById(1l);
    // then
    assertThat(foundSpeciality).isNotNull();
    then(specialtyRepository).should(times(1)).findById(anyLong());
  }

  @Test
  void deleteObject() {
    Speciality speciality = new Speciality();
    service.delete(speciality);
    verify(specialtyRepository, times(1))
        .delete(any(Speciality.class)); // подтверждаем, что метод delete() был вызван 1 раз с любым объектом имеющим тип Speciality
  }

  /*
  Implement BDD concept
   */
  @Test
  void deleteObjectBDD() {
    // given
    Speciality speciality = new Speciality();
    //when
    service.delete(speciality);
    //then
    then(specialtyRepository).should(times(1)).delete(any(Speciality.class));
  }

  /*
  Standard TDD
   */
  @Test
  void testDoThrow() {
    doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
    assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));
    verify(specialtyRepository, times(1)).delete(any());
  }

  /*
Implement BDD concept
 */
  @Test
  void testDoThrowBDD() {
    given(specialtyRepository.findById(anyLong())).willThrow(new RuntimeException("boom"));
    assertThrows(RuntimeException.class, () -> service.findById(1L));
    then(specialtyRepository).should(times(1)).findById(1L);
  }

  @Test
  void testDeleteBDD() {
    willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any()); // if method under test return void
    assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));
    then(specialtyRepository).should().delete(any());
  }

  /*
  Необходимость выполнения условия, чтобы началось обучения мока.
   */
  @Test
  void testSaveLambdaMatch() {
    //given
    final String MATCH_ME = "MATCH_ME";
    Speciality speciality = new Speciality();
//    speciality.setDescription("Not a match"); // не будет equal
    speciality.setDescription("MATCH_ME"); // будет equal

    Speciality savedSpecialty = new Speciality();
    savedSpecialty.setId(1L);

    //need mock to only return on match MATCH_ME string. argThat - allows creating custom argument matchers.
    given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

    //when
    Speciality returnedSpecialty = service.save(speciality);

    //then
//    assertNull(returnedSpecialty);
    assertThat(returnedSpecialty).isNotNull();
  }

}