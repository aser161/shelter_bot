package com.example.shelter_bot.listener;

import com.example.shelter_bot.key_board.KeyBoardShelter;
import com.example.shelter_bot.repository.PersonCatRepository;
import com.example.shelter_bot.repository.PersonDogRepository;
import com.example.shelter_bot.service.ReportDataService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.example.shelter_bot.repository.ReportDataRepository;
import com.example.shelter_bot.model.*;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс-сервис: читает сообщения пользователя и отпровляет свои
 *
 * @author aser161
 * @version 1.0.0
 */
@Service
public class ShelterBotUpdatesListener implements UpdatesListener {

    private static final String infoAboutBot = "Информация о возможностях бота \n- Бот может показать информацию о приюте \n" +
            "- Покажет какие документы нужны \n- Бот может принимать ежедневный отчет о питомце\n" +
            "- Может передать контактные данные волонтерам для связи";
    private static final String infoAboutShelterDog = "Наш сайт с информацией о приюте для собак \nhttps://google.com \n" +
            "Контактные данные \nhttps://yandex.ru\n" +
            "Общие рекомендации \nhttps://ru.wikipedia.org\n" +
            "";
    private static final String infoAboutShelterCat = "Наш сайт с информацией о приюте для кошек \nhttps://google.com \n" +
            "Контактные данные \nhttps://yandex.ru\n" +
            "Общие рекомендации \nhttps://ru.wikipedia.org\n" +
            "";
    private static final String infoAboutDogs = "Правила знакомства с животным \nhttps://google.com \n" +
            "Список документов \nhttps://yandex.ru\n" +
            "Список рекомендаций \nhttps://ru.wikipedia.org\n" +
            "Советы кинолога \nhttps://ru.wikipedia.org\n" +
            "Прочая информация \nhttps://google.com\n" +
            "";

    private static final String infoAboutCats = "Правила знакомства с животным \nhttps://google.com \n" +
            "Список документов \nhttps://yandex.ru\n" +
            "Список рекомендаций \nhttps://ru.wikipedia.org\n" +
            "Прочая информация \nhttps://google.com\n" +
            "";
    private static final String infoContactsVolonter = "Контактные данные волонтера  \n @a1ekseev_artem \n" +
            "Телефон - +7 999 999 99 99 \n";
    private static final String infoAboutReport = "Для отчета нужна следующая информация:\n" +
            "- Фото животного.  \n" +
            "- Рацион животного\n" +
            "- Общее самочувствие и привыкание к новому месту\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.\nСкопируйте следующий пример. Не забудьте прикрепить фото";

    private static final String reportExample = "Рацион: ваш текст;\n" +
            "Самочувствие: ваш текст;\n" +
            "Поведение: ваш текст;";

    private static final String REGEX_MESSAGE = "(Рацион:)(\\s)(\\W+)(;)\n" +
            "(Самочувствие:)(\\s)(\\W+)(;)\n" +
            "(Поведение:)(\\s)(\\W+)(;)";

    private Logger logger = LoggerFactory.getLogger(ShelterBotUpdatesListener.class);

    private long daysOfReports;

    private static final long telegramChatVolunteers = -745255418L;
    @Autowired
    private ReportDataRepository reportRepository;
    @Autowired
    private PersonDogRepository personDogRepository;
    @Autowired
    private PersonCatRepository personCatRepository;
    private boolean isCat = false;
    @Autowired
    private KeyBoardShelter keyBoardShelter;

    @Autowired
    private ReportDataService reportDataService;
    private final TelegramBot telegramBot;

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    public ShelterBotUpdatesListener( TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Обрабатывает
     *
     * @param updates
     * @return {@link UpdatesListener#CONFIRMED_UPDATES_ALL}
     */

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .forEach(update -> {
                        logger.info("Processing update: {}", update);
                        String nameUser = update.message().chat().firstName();
                        Message message = update.message(); //получаем сообщение
                        Long chatId = message.chat().id(); //получаем идентификатор чата
                        Integer messageId = update.message().messageId();
                        String text = message.text();     //получаем текст сообщения
                        Calendar calendar = new GregorianCalendar();

                        daysOfReports = reportRepository.findAll().stream()
                                .filter(s -> s.getChatId() == chatId)
                                .count() + 1;

                        long compareTime = calendar.get(Calendar.DAY_OF_MONTH);

                        Long lastMessageTime = reportRepository.findAll().stream()
                                .filter(s -> s.getChatId() == chatId)
                                .map(ReportData::getLastMessageMs)
                                .max(Long::compare)
                                .orElseGet(() -> null);
                        if (lastMessageTime != null) {
                            Date lastDateSendMessage = new Date(lastMessageTime * 1000);
                            long numberOfDay = lastDateSendMessage.getDate();

                            if (daysOfReports < 30 ) {
                                if (compareTime != numberOfDay) {
                                    if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                                        getReport(update);
                                    }
                                } else {
                                    if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                                        sendMessage(chatId, "Вы уже отправляли отчет сегодня");
                                    }
                                }
                                if (daysOfReports == 31) {
                                    sendMessage(chatId, "Вы прошли испытательный срок!");
                                }
                            }
                        } else {
                            if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                                getReport(update);
                            }
                        }

                        if (update.message() != null && update.message().photo() != null && update.message().caption() == null) {
                            sendMessage(chatId, "Отчет нужно присылать с описанием!");
                        }

                        if (update.message() != null && update.message().contact() != null) {
                            shareContact(update);
                        }

                        switch (text) {

                            case "/start":
                                sendMessage(chatId, nameUser + GREETING_TEXT);
                                keyBoardShelter.chooseMenu(chatId);
                                break;

                            case "Выбрать кота":

                                isCat = true;
                                keyBoardShelter.sendMenu(chatId);
                                sendMessage(chatId, "Вы выбрали кошку, МЯУ:D");
                                break;
                            case "Выбрать собаку":

                                isCat = false;
                                keyBoardShelter.sendMenu(chatId);
                                sendMessage(chatId, "Вы выбрали собаку, ГАВ:D");
                                break;

                            case "Главное меню":
                                keyBoardShelter.sendMenu(chatId);
                                break;
                            case "Узнать информацию о приюте":
                                keyBoardShelter.sendMenuInfoShelter(chatId);
                                break;
                            case "Информация о приюте":
                                if (isCat) {
                                    sendMessage(chatId, infoAboutShelterCat);
                                } else {
                                    sendMessage(chatId, infoAboutShelterDog);
                                }
                                break;
                            case "Советы и рекомендации":
                                if (isCat) {
                                    sendMessage(chatId, infoAboutCats);
                                    ;
                                    break;
                                } else {
                                    sendMessage(chatId, infoAboutDogs);
                                    break;
                                }
                            case "Прислать отчет о питомце":
                                sendMessage(chatId, infoAboutReport);
                                sendMessage(chatId, reportExample);
                                break;
                            case "Как взять питомца из приюта":
                                keyBoardShelter.sendMenuTakeAnimal(chatId);
                                break;
                            case "Информация о возможностях бота":
                                sendMessage(chatId, infoAboutBot);
                                break;
                            case "Вернуться в меню":
                                keyBoardShelter.sendMenu(chatId);
                                break;
                            case "Привет":
                                if (messageId != null) {
                                    sendReplyMessage(chatId, "И тебе привет", messageId);
                                    break;
                                }
                            case "Позвать волонтера":
                                sendMessage(chatId, "Мы передали ваше сообщение волонтерам. " +
                                        "Если у вас закрытый профиль - поделитесь контактом. " +
                                        "Справа сверху 3 точки - отправить свой телефон");
                                sendForwardMessage(chatId, messageId);
                                break;
                            case "":
                                System.out.println("Нельзя");
                                sendMessage(chatId, "Пустое сообщение");
                                break;
                            default:
                                sendReplyMessage(chatId, "Я не знаю такой команды", messageId);
                                break;
                        }

                    });
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void getReport(Update update) {
        Pattern pattern = Pattern.compile(REGEX_MESSAGE);
        Matcher matcher = pattern.matcher(update.message().caption());
        if (matcher.matches()) {
            String ration = matcher.group(3);
            String health = matcher.group(7);
            String habits = matcher.group(11);

            GetFile getFileRequest = new GetFile(update.message().photo()[1].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {
                File file = getFileResponse.file();
                file.fileSize();
                String fullPathPhoto = file.filePath();

                long timeDate = update.message().date();
                Date dateSendMessage = new Date(timeDate * 1000);
                byte[] fileContent = telegramBot.getFileContent(file);
                reportDataService.uploadReportData(update.message().chat().id(), fileContent, file,
                        ration, health, habits, fullPathPhoto, dateSendMessage, timeDate, daysOfReports);

                telegramBot.execute(new SendMessage(update.message().chat().id(), "Отчет успешно принят!"));

                System.out.println("Отчет успешно принят от: " + update.message().chat().id());
            } catch (IOException e) {
                System.out.println("Ошибка загрузки фото!");
            }
        } else {
            GetFile getFileRequest = new GetFile(update.message().photo()[1].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {
                File file = getFileResponse.file();
                file.fileSize();
                String fullPathPhoto = file.filePath();

                long timeDate = update.message().date();
                Date dateSendMessage = new Date(timeDate * 1000);
                byte[] fileContent = telegramBot.getFileContent(file);
                reportDataService.uploadReportData(update.message().chat().id(), fileContent, file, update.message().caption(),
                        fullPathPhoto, dateSendMessage, timeDate, daysOfReports);

                telegramBot.execute(new SendMessage(update.message().chat().id(), "Отчет успешно принят!"));
                System.out.println("Отчет успешно принят от: " + update.message().chat().id());
            } catch (IOException e) {
                System.out.println("Ошибка загрузки фото!");
            }
        }
    }

    private void shareContact(Update update) {
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String phone = update.message().contact().phoneNumber();
            String username = update.message().chat().username();
            long finalChatId = update.message().chat().id();
            var sortChatId = personDogRepository.findAll().stream()
                    .filter(i -> i.getChatId() == finalChatId)
                    .collect(Collectors.toList());
            var sortChatIdCat = personCatRepository.findAll().stream()
                    .filter(i -> i.getChatId() == finalChatId)
                    .collect(Collectors.toList());

            if (!sortChatId.isEmpty() || !sortChatIdCat.isEmpty()) {
                sendMessage(finalChatId, "Вы уже в базе!");
                return;
            }
            if (lastName != null) {
                String name = firstName + " " + lastName + " " + username;
                if(isCat){
                    personCatRepository.save(new PersonCat(name, phone, finalChatId));
                } else {
                    personDogRepository.save(new PersonDog(name, phone, finalChatId));
                }
                sendMessage(finalChatId, "Вас успешно добавили в базу. Скоро вам перезвонят.");
                return;
            }
            if (isCat) {
                personCatRepository.save(new PersonCat(firstName, phone, finalChatId));
            } else {
                personDogRepository.save(new PersonDog(firstName, phone, finalChatId));
            }
            sendMessage(finalChatId, "Вас успешно добавили в базу! Скоро вам перезвонят.");
            // Сообщение в чат волонтерам
            sendMessage(telegramChatVolunteers, phone + " " + firstName + " Добавил(а) свой номер в базу");
            sendForwardMessage(finalChatId, update.message().messageId());
        }
    }

    private void sendForwardMessage(Long chatId, Integer messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(telegramChatVolunteers, chatId, messageId);
        telegramBot.execute(forwardMessage);
    }

    private void sendReplyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }


    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Произошла ошибка!!! {}",sendResponse.description());
        }
    }

}


