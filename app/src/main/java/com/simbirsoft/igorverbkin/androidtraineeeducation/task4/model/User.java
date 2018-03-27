package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User implements Serializable {

    private String photo;
    private String secondName;
    private String firstName;
    private String birthday;
    private String fieldActivity;
    private String password;
    private String email;
    private String phoneNumber;
    private List<History> history;

    public void addHistory(String id, String description) {
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(new History(id, description));
    }
}
