package com.example.shelter_bot.entity;


import javax.persistence.*;

@Entity
@Table(name = "notification_users")

public class NotificationUser {
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

