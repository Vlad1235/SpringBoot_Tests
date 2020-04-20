package com.learnmockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.learntotestviajunit5.repositories.VetRepository;
import com.learntotestviajunit5.services.springdatajpa.VetSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

  @Mock
  VetRepository vetRepository;

  @InjectMocks
  VetSDJpaService service;

  @Test
  void deleteById() {
    service.deleteById(1L); // вызываем
    verify(vetRepository,times(1)).deleteById(1L); // проверяем, что действительно вызван и сколько раз
  }
}