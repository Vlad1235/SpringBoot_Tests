package com.learntotestviajunit5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
Классы которые реализуют данный интерфейс, уже будут помечены тегом @Tag("controllers")
 */

@Tag("controllers")
@TestInstance(Lifecycle.PER_CLASS) // for @BeforeAll to work without static definition
public interface ControllersTest {

  // метод, который будет для ВСЕХ классов, кто реализует этот интерфейс
  @BeforeAll
  default void beforeAll(){
    System.out.println("Lets do something once but FOR EVERYBODY");
  }
}
