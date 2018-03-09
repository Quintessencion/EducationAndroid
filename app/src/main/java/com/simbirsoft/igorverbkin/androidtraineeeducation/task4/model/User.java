package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String uri;
    private String secondName;
    private String firstName;
    private String birthday;
    private String fieldActivity;
    private String password;
    private String email;
    private String phoneNumber;
    private List<History> history;

    public User() {
    }

    public String getPhoto() {
        return uri;
    }

    public void setPhoto(String uri) {
        this.uri = uri;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFieldActivity() {
        return fieldActivity;
    }

    public void setFieldActivity(String fieldActivity) {
        this.fieldActivity = fieldActivity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public void addHistory(String id, String description) {
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(new History(id, description));
    }
}
