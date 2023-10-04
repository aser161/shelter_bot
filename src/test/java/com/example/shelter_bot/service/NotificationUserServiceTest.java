package com.example.shelter_bot.service;

import com.example.shelter_bot.entity.NotificationUser;
import com.example.shelter_bot.repository.NotificationUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationUserServiceTest {

    @Mock
    private NotificationUserRepository notificationUserRepository;

    @InjectMocks
    private NotificationUserService notificationUserService;


    @Test
    public void saveTest(NotificationUser notificationUser){
        Assertions.assertThat(notificationUserService.save(notificationUser).isEqualTo(true);
    }



}
