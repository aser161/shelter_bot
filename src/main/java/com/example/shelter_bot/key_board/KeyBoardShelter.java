package com.example.shelter_bot.key_board;

import com.example.shelter_bot.listener.ShelterBotUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.vdurmont.emoji.EmojiParser;

/**
 * A <b>custom</b> class that implements <b>buttons</b> in the telegram bot interface.
 * @author aser161
 * @version 1.0.0
 */
@Service
public class KeyBoardShelter {

    @Autowired
    private TelegramBot telegramBot;

    private static final Logger logger = LoggerFactory.getLogger(ShelterBotUpdatesListener.class);

    /**
     * A method for displaying a menu with a basic set of buttons.
     * @param chatId
     * @see KeyBoardShelter
     */
    public void chooseMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано меню выбора ");

//        String emoji_cat = EmojiParser.parseToUnicode(":cat:");
//        String emoji_dog = EmojiParser.parseToUnicode(":dog:");
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
//                new KeyboardButton(emoji_cat + " CAT"));
//        replyKeyboardMarkup.addRow(new KeyboardButton(emoji_dog + " DOG"));
//        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Выберите, кого хотите приютить:");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Выбрать кота"),
                new KeyboardButton("Выбрать собаку"));


        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
    }

    /**
     * A method for displaying a menu with a basic set of buttons.
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано основное меню ");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Информация о возможностях бота"),
                new KeyboardButton("Узнать информацию о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Как взять питомца из приюта"),
                new KeyboardButton("Прислать отчет о питомце"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));

        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
    }

    /**
     * The way to display the menu with information about the shelter.
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenuInfoShelter(long chatId) {
        logger.info("Method sendMenuInfoShelter has been run: {}, {}", chatId, "Вызвали ~Информация о приюте~");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Информация о приюте"),
                new KeyboardButton("Оставить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Информация о приюте");
    }

    /**
     * A way to display a menu with information about how to take a pet.
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenuTakeAnimal(long chatId) {
        logger.info("Method sendMenuTakeAnimal has been run: {}, {}", chatId, "вызвали ~Как взять питомца из приюта~");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Советы и рекомендации"),
                new KeyboardButton("Оставить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Как взять питомца из приюта");
    }

    /**
     * The return method is the response from.
     * @param replyKeyboardMarkup
     * @param chatId
     * @param text
     * @see KeyBoardShelter
     */
    public void returnResponseReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);

        SendMessage request = new SendMessage(chatId, text)
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }
}
