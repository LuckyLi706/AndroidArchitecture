package com.jackyli.androidarchitecture.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {

    @PrimaryKey
    private int id;

    private String userName;

    private String passWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
