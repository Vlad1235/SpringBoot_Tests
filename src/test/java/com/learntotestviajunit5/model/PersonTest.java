package com.learntotestviajunit5.model;

import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.ModelTest;
import com.learntotestviajunit5.RepeatedModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

//@Tag("model") релизует интерфейс
class PersonTest implements ModelTest {

  @Test
  void groupedAssertions() {
    // given
    Person person = new Person((long) 11, "Joe", "Buck");
    // then
    assertAll("Test props are:",
        () -> assertEquals("Joe",person.getFirstName()),
        () -> assertEquals("Buck",person.getLastName())
    );
  }

  @Test
  void groupedAssertionMsgs() { // все или не один должны пройти
    //given
    Person person = new Person(1l, "Joe", "Buck");
    //then
    assertAll("Test Props Set",
        () -> assertEquals("Joe",person.getFirstName(), "First Name Failed"),
        () -> assertEquals("Buck",person.getLastName(), "Last Name Failed"));
  }


  @Nested
  class RepeatedTests implements RepeatedModelTest {

    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition} - {totalRepetitions}") // запускать данный тест подряд 5 раз
    @DisplayName("My repeated test ")
    void myRepeatedTest() {
      //todo
    }

    /*
    JUnit5 Dependency Injection
    Используем заложенные в JUnit5 классы
    TestInfo,RepetitionInfo,TestReporter для получения доп информации о самом тесте. Больше для метаданных
     */
    @RepeatedTest(5)
    @DisplayName("My repeated test2 ")
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
      System.out.println(testInfo.getDisplayName() + " : " + repetitionInfo.getTotalRepetitions());
    }


    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition} - {totalRepetitions}")
    @DisplayName("my cool test ")
    void myAssignmentRepeated() {
      //todo
    }
  }
}