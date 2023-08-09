package com.example.shelter_bot.service;

import com.example.shelter_bot.entity.NotificationUser;
import com.example.shelter_bot.repository.NotificationUserRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationUserService {
  private final NotificationUserRepository notificationUserRepository;


  public NotificationUserService(NotificationUserRepository notificationUserRepository) {
    this.notificationUserRepository = notificationUserRepository;
  }
  public void save(NotificationUser notificationUser){

    notificationUserRepository.save(notificationUser);
  }



}
