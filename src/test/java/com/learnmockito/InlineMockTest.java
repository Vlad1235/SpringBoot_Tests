package com.learnmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * 1 способ создания моков
 */
public class InlineMockTest {

  @Test
  void testInlineMock() {
    Map mapMock = mock(Map.class);
    assertEquals(mapMock.size(),0); // default behavior of mockito return 0
  }
}
