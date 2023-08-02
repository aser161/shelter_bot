package com.example.shelter_bot.entity;


import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (appliesTo = "notification_users")

public class NotificationUser {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="user_id",nullable = false)
    private long userId;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="user_telephone", nullable = false)
    private int userTelephone;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(int userTelephone) {
        this.userTelephone = userTelephone;
    }
}
