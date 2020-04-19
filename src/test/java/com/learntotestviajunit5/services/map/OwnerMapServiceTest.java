package com.learntotestviajunit5.services.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.learntotestviajunit5.model.Owner;
import com.learntotestviajunit5.model.PetType;
import com.learntotestviajunit5.services.PetService;
import com.learntotestviajunit5.services.PetTypeService;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Вложенные циклы. У каждого отдельный цикл
 */
@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

  OwnerMapService ownerMapService;
  PetTypeService petTypeService;
  PetService petService;

  @BeforeEach
  void setUp() {
    petTypeService = new PetTypeMapService();
    petService = new PetMapService();
    ownerMapService = new OwnerMapService(petTypeService, petService);
    System.out.println("First before each");
  }

  @DisplayName("Verify Zero Owners")
  @Test
  void ownersAreZero() {
    int ownerCount = ownerMapService.findAll().size();
    assertThat(ownerCount).isZero();
  }

  @DisplayName("Pet Type - ")
  @Nested
  class TestCreatePetTypes {

    @BeforeEach
    void setUp() {
      PetType petType = new PetType(1L, "Dog");
      PetType petType2 = new PetType(2L, "Cat");
      petTypeService.save(petType);
      petTypeService.save(petType2);
      System.out.println("Nested before each");
    }

    @DisplayName("Test Pet Count")
    @Test
    void testPetCount() {
      int petTypeCount = petTypeService.findAll().size();
      assertThat(petTypeCount).isNotZero().isEqualTo(2);
    }

    @DisplayName("Save Owners Tests - ")
    @Nested
    class SaveOwnersTests {

      List<Owner> list = null;

      @BeforeEach
      void setUp() {
        Owner owner = new Owner(1L, "Before", "Each");
        ownerMapService.save(owner);
        list = Lists.list(owner);
        System.out.println("Nester 2 level before each");
      }

      @DisplayName("Save Owner")
      @Test
      void saveOwner() {
        Owner owner2 = new Owner(2L, "Joe", "Buck");
        Owner savedOwner = ownerMapService.save(owner2);
        list.add(owner2);
        assertThat(savedOwner).isNotNull();
        assertThat(list).hasSize(2);
        System.out.println("result");
      }
      /*
      First before each
      Nested before each
      Nester 2 level before each
      result
       */

      @DisplayName("Save Owners Tests - ")
      @Nested
      class FindOwnersTests {

        @DisplayName("Find Owner")
        @Test
        void findOwner() {

          Owner foundOwner = ownerMapService.findById(1L);

          assertThat(foundOwner).isNotNull();
        }

        @DisplayName("Find Owner Not Found")
        @Test
        void findOwnerNotFound() {

          Owner foundOwner = ownerMapService.findById(2L);

          assertThat(foundOwner).isNull();
        }
      }
    }
  }

  @DisplayName("Verify Still Zero Owners")
  @Test
  void ownersAreStillZero() {
    int ownerCount = ownerMapService.findAll().size();

    assertThat(ownerCount).isZero(); // так как циклы отдельные у каждого nested то в итоге будет 0
  }

}