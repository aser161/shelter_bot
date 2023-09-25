package com.example.shelter_bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Interface CatRepository.
 * @author Lubava
 * @version 1.0.0
 */

@Configuration

public class ShelterBotConfiguration {
    @Bean
    public TelegramBot telegramBot(@Value("${telegram.bot.token}")String token){

        return new TelegramBot(token);
//        TelegramBot bot = new TelegramBot(token);
//        bot.execute(new DeleteMyCommands());
//        return bot;
    }
}
