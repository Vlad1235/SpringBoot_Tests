package com.learnmockito;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * То же самое что и в классе InlineMockTest, только через аннотации
 * 2 способ
 */
public class AnnotationMocksTest {

  @Mock
  Map<String, Object> mapMock;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testMock() {
    mapMock.put("key","someObject");

  }
}
