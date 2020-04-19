package com.learntotestviajunit5.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled(value = "disabled until we learn mocking") // весь класс в игнор
class OwnerSDJpaServiceTest {

  OwnerSDJpaService service;

  @BeforeEach
  void setUp() {
    service = new OwnerSDJpaService(null,null,null);
  }


  @Test
  void findByLastName() {
    Owner foundOwner = service.findByLastName("Buck");
  }

  @Test
  void findAllByLastNameLike() {
  }

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