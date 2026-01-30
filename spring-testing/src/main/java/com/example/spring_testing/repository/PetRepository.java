package com.example.spring_testing.repository;

import com.example.spring_testing.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByBreed(String breed);

}
