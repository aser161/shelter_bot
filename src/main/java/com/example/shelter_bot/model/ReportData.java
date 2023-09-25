package com.example.shelter_bot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Class of owner for the dog.
 * @author aser161
 * @version 1.0.0
 */

@Entity
public class ReportData {

    /** "ID" field */
    @Id
    @GeneratedValue
    private long id;

    /** "id Chat" field */
    private Long chatId;

    /** "Ration" field */
    private String ration;

    /** "Health" field */
    private String health;

    /** "Habits" field */
    private String habits;

    /** "Days" field */
    private long days;

    /** "File Path" field */
    private String filePath;

    /** "File Size" field */
    private long fileSize;

    /** "Data" field */
    @Lob
    private byte[] data;

    /** "Caption" field */
    private String caption;

    /** "LastMessage" field */
    private Date lastMessage;

    /** "LastMessageMs" field */
    private Long lastMessageMs;

    /**
     * Constructor - creating a new object.
     * @see ReportData
     */
    public ReportData() {
    }

    /**
     * Constructor - creating a new object with certain values.
     * @param chatId
     * @param data
     */
    public ReportData(Long chatId, byte[] data) {
        this.chatId = chatId;
        this.data = data;
    }

    /**
     * Constructor - creating a new object with certain values.
     * @param ration
     * @param health
     * @param habits
     */
    public ReportData(String ration, String health, String habits) {
        this.ration = ration;
        this.health = health;
        this.habits = habits;
    }

    /**
     * Constructor - creating a new object with certain values.
     * @param chatId
     * @param data
     * @param ration
     * @param health
     * @param habits
     */
    public ReportData(Long chatId, byte[] data,
                      String ration, String health, String habits) {
        this.chatId = chatId;
        this.data = data;
        this.ration = ration;
        this.health = health;
        this.habits = habits;
    }

    public Long getLastMessageMs() {
        return lastMessageMs;
    }

    public void setLastMessageMs(Long lastMessageMs) {
        this.lastMessageMs = lastMessageMs;
    }

    public Date getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Date lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportData that = (ReportData) o;
        return id == that.id && Objects.equals(ration, that.ration) && Objects.equals(health, that.health) && Objects.equals(habits, that.habits) && Objects.equals(days, that.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ration, health, habits, days);
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", ration='" + ration + '\'' +
                ", health='" + health + '\'' +
                ", habits='" + habits + '\'' +
                ", days=" + days +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", data=" + Arrays.toString(data) +
                ", caption='" + caption + '\'' +
                ", lastMessage=" + lastMessage +
                ", lastMessageMs=" + lastMessageMs +
                '}';
    }

}
