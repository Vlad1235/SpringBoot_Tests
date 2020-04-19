package com.learntotestviajunit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/**
 * Классы которые реализуют данный интерфейс, уже будут помечены тегом @Tag("model")
 */
@Tag("model")
public interface ModelTest {

  /*
Для каждого класса кто реализует данный интерфейс, данный метод будет запускаться для КАЖДОГО метода в классе
   */
  @BeforeEach
  default void beforeEach(TestInfo testInfo) {
    System.out.println("Running test " + testInfo.getDisplayName());
    // some logic
  }

}
