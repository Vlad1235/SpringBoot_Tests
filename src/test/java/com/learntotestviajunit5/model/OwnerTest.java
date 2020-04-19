package com.learntotestviajunit5.model;


import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.CustomArgsProvider;
import com.learntotestviajunit5.ModelTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//@Tag("model")
class OwnerTest implements ModelTest {

  /*
  Класс наследует класс Person. Можем в одном unit тесте проверить оба класса
   */
  @Test
  void dependentAssertions() {

    Owner owner = new Owner(1l, "Joe", "Buck");
    owner.setCity("Key West");
    owner.setTelephone("1231231234");
// вложенные assertAll
    assertAll("Properties Test",
        () -> assertAll("Person Properties",
            () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
            () -> assertEquals("Buck", owner.getLastName())),
        () -> assertAll("Owner Properties",
            () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
            () -> assertEquals("1231231234", owner.getTelephone())
        ));
  }

  /*
  Концепция параметеризованный тестов. Будут подставляться значения в тест.Можно их использовать
  Последовательно подставляться будет каждое значение из(массива, enum и тд) в тест
   */
  @DisplayName("Value Source test ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @ValueSource(strings = {"String","Framework","Java"})
  void testValueSource(String val){
    System.out.println(val);
  }

  @DisplayName("Enum Source test ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @EnumSource(OwnerType.class)
  void enumTest(OwnerType ownerType){
    System.out.println(ownerType);
  }

  @DisplayName("CSV input test ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @CsvSource({"FL,1,1",
              "OH,1,2",
              "MI,2,2"
          })
  void csvInputTest(String stateName, int val1, int val2){
    System.out.println(stateName+" = "+val1+":"+val2);
  }

  @DisplayName("CSV from file input.csv in resource directory ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @CsvFileSource(resources = "/input.csv",numLinesToSkip = 1) // numLinesToSkip так как первая строка в файле это заголовок
  void csvInputTestFromFile(String stateName, int val1, int val2){
    System.out.println(stateName+" = "+val1+":"+val2);
  }

  @DisplayName("Method Provider Test ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @MethodSource("getArgs")
  void fromMethodTest(String stateName, int val1, int val2){
    System.out.println(stateName+" = "+val1+":"+val2);
  }
  // метод, ктр выполнит любую нужную нам логику
  static Stream<Arguments> getArgs(){
    return Stream.of(
        Arguments.of("FL",1,1),
        Arguments.of("OH",1,2),
        Arguments.of("MI",2,5));
  }


  @DisplayName("Custom Provider Test ")
  @ParameterizedTest(name="{displayName} - [{index}] {arguments}")
  @ArgumentsSource(CustomArgsProvider.class)
  void fromCustomProviderTest(String stateName, int val1, int val2){
    System.out.println(stateName+" = "+val1+":"+val2);
  }

}