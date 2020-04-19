package com.learntotestviajunit5.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.learntotestviajunit5.ControllersTest;
import java.time.Duration;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

//@Tag("controllers") так как интерфейс уже имеет данную аннотацию
class IndexControllerTest implements ControllersTest {

  IndexController indexController;

  @BeforeEach
  void setUp() {
    indexController = new IndexController();
  }

  @DisplayName("Some meaningful name")
  @Test
  void index() {
    assertEquals("index", indexController.index());
    assertEquals("index", indexController.index(), "suppose to good");
    assertEquals("index", indexController.index(), () -> "If you see it that means test failed"); // лябмда только если тест упадет
  }

  @Test
  void oupsHandler() {
    assertTrue("notimplemented".equals(indexController.oupsHandler()), () -> "This is the message");  // тест не упал. Выражения не будет
  }

  @Test
  @DisplayName("test to throw exception")
  void oupsHandlerWithExcpetion() {
    assertThrows(RuntimeException.class, () -> {
      indexController.oupsHandlerWithExcpetion();
    });
  }

  @Disabled
  @Test
  @DisplayName("Check that method will not exceed the supposed running time")
  void testTimeOut() {
    assertTimeout(Duration.ofMillis(100), () -> {
      // some method in test
      Thread.sleep(300);
      System.out.println("we got here");
    });
  }

  /*
  will be executed in a different thread than that of the calling code
   */
  @Disabled
  @Test
  @DisplayName("Check that method will not exceed the supposed running time")
  void testTimeOutPrompt() {
    assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      // some method in test
      Thread.sleep(200);
      System.out.println("we got here");
    });
  }

  @Test
  void testAssumptionTrue() {
    assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME"))); // на этом шаге тест отменяется. Тест ignore помечается.
    assertTrue(true,"we reached to here");
  }

  @Test
  void testAssumptionTrue2() {
    assumeTrue("GURU".equalsIgnoreCase("GURU"));
    assertTrue(true,"we reached to here");
  }

  @EnabledOnOs(OS.MAC)
  @Test
  void testMeOnMacOS() {
    assertTrue(true,"we reached to here");
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  void testMeOnWindows() {
    assertTrue(true,"we reached to here");
  }

  @EnabledOnJre(JRE.JAVA_8)
  @Test
  void testMeOnJava8() {
    assertTrue(true,"we reached to here");
  }

  @EnabledOnJre(JRE.JAVA_11)
  @Test
  void testMeOnJava11() {
    assertTrue(true,"we reached to here");
  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "jt")
  @Test
  void testIfUserJT() {
    assertTrue(true,"we reached to here");
  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
  @Test
  void testIfUserFred() {
    assertTrue(true,"we reached to here");
  }


  @DisplayName("Using AssertJ")
  @Test
  void assertjusage() {
    // JUnit5
    assertEquals("index", indexController.index(), () -> "If you see it that means test failed");
    // AssertJ
    assertThat(indexController.index()).isEqualTo("index");

  }

  @DisplayName("Using Hamcrest")
  @Test
  void hamcrestusage() {
    // JUnit5
    assertEquals("index", indexController.index(), () -> "If you see it that means test failed");
    // AssertJ
    assertThat(indexController.index()).isEqualTo("index");
    // Hamcrest
    org.hamcrest.MatcherAssert.assertThat(indexController.index(),is("index"));
  }

}