package com.example.shelter_bot.service;

import com.example.shelter_bot.model.ReportData;
import com.example.shelter_bot.exceptions.*;
import com.example.shelter_bot.repository.ReportDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportDataService {

    private final ReportDataRepository reportRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReportDataService.class);

    public ReportDataService(ReportDataRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Method to uploadReportData.
     * @param personId
     * @param pictureFile
     * @param file
     * @param ration
     * @param health
     * @param habits
     * @param filePath
     * @param dateSendMessage
     * @param timeDate
     * @param daysOfReports
     * @throws IOException
     * @see ReportDataService
     */
    public void uploadReportData(Long personId, byte[] pictureFile, File file, String ration, String health,
                                 String habits, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports) throws IOException {
        logger.info("Was invoked method to uploadReportData");

        ReportData report = new ReportData();

        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setLastMessageMs(timeDate);
        report.setChatId(personId);
        report.setData(pictureFile);
        report.setRation(ration);
        report.setHealth(health);
        report.setHabits(habits);
        this.reportRepository.save(report);
    }

    /**
     * Method to uploadReportData.
     * @param personId
     * @param pictureFile
     * @param file
     * @param caption
     * @param filePath
     * @param dateSendMessage
     * @param timeDate
     * @param daysOfReports
     * @throws IOException
     * @see ReportDataService
     */
    public void uploadReportData(Long personId, byte[] pictureFile, File file,
                                 String caption, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports) throws IOException {
        logger.info("Was invoked method to uploadReportData");

        ReportData report = new ReportData();//findById(personId);
        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setChatId(personId);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setCaption(caption);
        report.setLastMessageMs(timeDate);
        this.reportRepository.save(report);
    }

    /**
     * Method to get a report by id.
     * @param id
     * @return {@link ReportDataRepository#findById(Object)}
     * @see ReportDataService
     * @exception PersonCatNotFoundException
     */
    public ReportData findById(Long id) {
        logger.info("Was invoked method to get a report by id={}", id);

        return this.reportRepository.findById(id)
                .orElseThrow(ReportDataNotFoundException::new);
    }

    /**
     * Method to get a report by chatId.
     * @param chatId
     * @return {@link ReportDataRepository#findByChatId(Long)}
     * @see ReportDataService
     */
    public ReportData findByChatId(Long chatId) {
        logger.info("Was invoked method to get a report by chatId={}", chatId);

        return this.reportRepository.findByChatId(chatId);
    }

    /**
     * Method to findListByChatId a report by id.
     * @param chatId
     * @return {@link ReportDataRepository#findListByChatId(Long)}
     * @see ReportDataService
     */
    public Collection<ReportData> findListByChatId(Long chatId) {
        logger.info("Was invoked method to findListByChatId a report by id={}", chatId);

        return this.reportRepository.findListByChatId(chatId);
    }

    /**
     * Method to save a report.
     * @param report
     * @return {@link ReportDataRepository#findListByChatId(Long)}
     * @see ReportDataService
     */
    public ReportData save(ReportData report) {
        logger.info("Was invoked method to save a report");

        return this.reportRepository.save(report);
    }

    /**
     * Method to remove a report by id.
     * @param id
     * @see ReportDataService
     */
    public void remove(Long id) {
        logger.info("Was invoked method to remove a report by id={}", id);

        this.reportRepository.deleteById(id);
    }

    /**
     * Method to get all reports.
     * @return {@link ReportDataRepository#findAll()}
     * @see ReportDataService
     */
    public List<ReportData> getAll() {
        logger.info("Was invoked method to get all reports");

        return this.reportRepository.findAll();
    }

    /**
     * Method to get all reports.
     * @param pageNumber
     * @param pageSize
     * @return {@link ReportDataRepository#findAll()}
     * @see ReportDataService
     */
    public List<ReportData> getAllReports(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method to get all reports");

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return this.reportRepository.findAll(pageRequest).getContent();
    }

    /**
     * Method to getExtensions.
     * @param fileName
     * @see ReportDataService
     */
    private String getExtensions(String fileName) {
        logger.info("Was invoked method to getExtensions");

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
