package com.learntotestviajunit5;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * класс, метод которого будет снабжать аргументами наш тест метод
 */
public class CustomArgsProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(
        Arguments.of("FL",1,1),
        Arguments.of("OH",1,2),
        Arguments.of("MI",2,5));
  };

}
