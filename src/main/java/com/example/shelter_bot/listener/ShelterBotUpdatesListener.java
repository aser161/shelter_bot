package com.example.shelter_bot.listener;

import com.example.shelter_bot.service.NotificationUserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class ShelterBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(ShelterBotUpdatesListener.class);



    private final TelegramBot telegramBot;


    public ShelterBotUpdatesListener( TelegramBot telegramBot) {

        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {

        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message()!=null)
                    .forEach(update -> {
                        logger.info("Processing update: {}", update);
                        Message message = update.message(); //получаем сообщение
                        Long chatId = message.chat().id(); //получаем идентификатор чата
                        String text = message.text();     //получаем текст сообщения

                        if ("/start".equals(text)) {

                           SendMessage sendMessage=new SendMessage(chatId, """
                                    Привет! Это бот приюта для кошек и собак!
                                    Кого бы вы хотели себе выбрать?
                                                """);
                            InlineKeyboardButton buttonCat=new InlineKeyboardButton("Кошку");
                            buttonCat.callbackData("Кошку");
                            InlineKeyboardButton buttonDog=new InlineKeyboardButton("Собаку");
                            buttonDog.callbackData("Собаку");
                            Keyboard keyboard=new InlineKeyboardMarkup(buttonCat,buttonDog);
                            sendMessage.replyMarkup(keyboard);
                            telegramBot.execute(sendMessage);

                        } else if(text !=null) {

                            sendMessage(chatId, "Некорректный тип данных");
                        }
                          if(update.callbackQuery() != null && "Кошку".equals(update.callbackQuery().data())) {
                            // нажата кнопка про кошек

                              telegramBot.execute(new SendMessage(chatId, "Добро пожаловать в приют для кошек."));
                        }  if(update.callbackQuery() != null && "Собаку".equals(update.callbackQuery().data())) {
                            // нажата кнопка про собак
                            telegramBot.execute(new SendMessage(chatId, "Добро пожаловать в приют для собак."));
                        }


                    });
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Произошла ошибка!!! {}",sendResponse.description());
        }
    }

}


