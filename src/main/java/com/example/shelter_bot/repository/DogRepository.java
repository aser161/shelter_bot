package com.example.shelter_bot.repository;

import com.example.shelter_bot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface DogRepository.
 * @author aser161
 * @version 1.0.0
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
