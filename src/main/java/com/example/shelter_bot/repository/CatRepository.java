package com.example.shelter_bot.repository;

import com.example.shelter_bot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CatRepository.
 * @author aser161
 * @version 1.0.0
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
