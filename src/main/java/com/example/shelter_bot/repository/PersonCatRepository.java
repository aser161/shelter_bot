package com.example.shelter_bot.repository;

import com.example.shelter_bot.model.PersonCat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

/**
 * Interface PersonCatRepository.
 * @author aser161
 * @version 1.0.0
 */
public interface PersonCatRepository extends JpaRepository<PersonCat, Long> {

    Set<PersonCat> findByChatId(Long chatId);

}
