package com.learnmockito.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyCollectionOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learntotestviajunit5.model.Visit;
import com.learntotestviajunit5.repositories.VisitRepository;
import com.learntotestviajunit5.services.springdatajpa.VisitSDJpaService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

  @Mock
  VisitRepository visitRepository;

  @InjectMocks
  VisitSDJpaService service;

  @DisplayName("Test Find All")
  @Test
  void findAll() {
    Visit visit = new Visit();

    Set<Visit> visits = new HashSet<>();
    visits.add(visit);

    when(visitRepository.findAll()).thenReturn(visits);

    Set<Visit> foundVisits = service.findAll();

    verify(visitRepository,times(1)).findAll();

    assertThat(foundVisits).hasSize(1);

  }

  @Test
  void findById() {
    Visit visit = new Visit();

    when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

    Visit foundVisit = service.findById(1L);

    verify(visitRepository,times(1)).findById(anyLong());

    assertThat(foundVisit).isNotNull();
  }

  @Test
  void save() {
    Visit visit = new Visit();

    when(visitRepository.save(any(Visit.class))).thenReturn(visit);

    Visit savedVisit = service.save(new Visit());

    verify(visitRepository,times(1)).save(any(Visit.class));

    assertThat(savedVisit).isNotNull();
  }

  @Test
  void delete() {
    Visit visit = new Visit();

    service.delete(visit);

    verify(visitRepository,times(1)).delete(any(Visit.class));

  }

  @Test
  void deleteById() {

    service.deleteById(1L);

    verify(visitRepository,times(1)).deleteById(anyLong());
  }
}