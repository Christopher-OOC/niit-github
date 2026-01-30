package com.example.spring_testing.repository;

import com.example.spring_testing.entity.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @BeforeAll
    void setUp() {
        Pet pet1 = new Pet();
        pet1.setAge(3);
        pet1.setName("pet1");
        pet1.setBreed("Cat");
        pet1.setGender("M");

        Pet pet2 = new Pet();
        pet2.setAge(5);
        pet2.setName("pet2");
        pet2.setBreed("Dog");
        pet2.setGender("F");

        Pet pet3 = new Pet();
        pet3.setAge(4);
        pet3.setName("pet3");
        pet3.setBreed("Cat");
        pet3.setGender("M");

        Pet pet4 = new Pet();
        pet4.setAge(3);
        pet4.setName("pet4");
        pet4.setBreed("Dog");
        pet4.setGender("F");

        petRepository.saveAll(List.of(pet1, pet2, pet3, pet4));
    }

    @Test
    void testSave_Success() {
        Pet newPet = new Pet();
        newPet.setAge(3);
        newPet.setName("newPet");
        newPet.setBreed("Cat");
        newPet.setGender("M");

        Pet savedPet = petRepository.save(newPet);

        Assertions.assertNotNull(savedPet);
        Assertions.assertEquals(newPet.getName(), savedPet.getName());
    }

    @Test
    void testSearchByBreed_Success() {
        String breed = "Dog";
        int expected = 2;

        List<Pet> search = petRepository.findByBreed(breed);

        Assertions.assertEquals(expected, search.size());
    }
}
