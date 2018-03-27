package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

public enum Category {
    CHILDREN(R.string.children, "CHILDREN"),
    ADULTS(R.string.adults, "ADULTS"),
    ELDERLY(R.string.elderly, "ELDERLY"),
    PETS(R.string.pets, "PETS"),
    EVENTS(R.string.events, "EVENTS");

    private final int resId;
    private final String name;

    Category(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }

    public int getRes() {
        return resId;
    }

    public String getName() {
        return name;
    }
}
