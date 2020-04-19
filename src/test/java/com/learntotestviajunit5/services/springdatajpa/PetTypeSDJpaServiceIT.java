package com.learntotestviajunit5.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.junitextensions.TimingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * 1. Класс который в конце помечен как IT у MAVEN рассматривается как содержащий интеграционные тесты
 * 2. @ExtendWith() аннотация позволяет подключать стороннюю(или собственную) реализцию для дополнительной логики проверки методов.
 */
@ExtendWith(TimingExtension.class) // подключаем third party extension более расширенную логику логирования времени исполнениня
class PetTypeSDJpaServiceIT {

  @Test
  void findAll() {
  }

  @Test
  void findById() {
  }

  @Test
  void save() {
  }

  @Test
  void delete() {
  }

  @Test
  void deleteById() {
  }
}