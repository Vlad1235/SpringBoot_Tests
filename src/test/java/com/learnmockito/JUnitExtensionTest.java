package com.learnmockito;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Третий способ создания mock
 */
@ExtendWith(MockitoExtension.class)
public class JUnitExtensionTest {

  @Mock
  Map<String,Object> mapMock;

  @Test
  void testMock() {
    mapMock.put("key","someObject"); // ничего не вернет. Мок не обучен что-либо возвращать
  }
}
