package com.example.shelter_bot.repository;

import com.example.shelter_bot.model.PersonDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * Interface PersonDogRepository.
 * @author aser161
 * @version 1.0.0
 */
@Repository
public interface PersonDogRepository extends JpaRepository<PersonDog, Long> {

    Set<PersonDog> findByChatId(Long chatId);

}