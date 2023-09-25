package com.example.shelter_bot.repository;

import com.example.shelter_bot.entity.NotificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface CatRepository.
 * @author Lubava
 * @version 1.0.0
 */

public interface NotificationUserRepository extends JpaRepository<NotificationUser,Long> {

   // List<NotificationUser> findAllByNotificationUAndUserIdAndChatIdAndChatId();


    // @Modifying
   // @Query("DELETE FROM NotificationUser WHERE message like %:nameLike%")
   // void removeAllLike(@Param("nameLike") String nameLike);

}
