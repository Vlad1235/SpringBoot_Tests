package com.learntotestviajunit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeatedModelTests")
public interface RepeatedModelTest {
  @BeforeEach
  default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
    System.out.println("Running test " + testInfo.getDisplayName() + repetitionInfo.getTotalRepetitions());
    // some logic
  }
}
