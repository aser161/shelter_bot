package com.example.shelter_bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ShelterBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(ShelterBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

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
                        Message message = update.message();
                        Long chatId = message.chat().id();
                        String text = message.text();

                        if ("/start".equals(text)) {
                            sendMessage(chatId, """
                                    Привет! Это бот приюта для кошек и собак!
                                    Кого бы вы хотели себе выбрать?
                                                """);

                        } else {
                            sendMessage(chatId, "Некорректный тип данных!");
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
