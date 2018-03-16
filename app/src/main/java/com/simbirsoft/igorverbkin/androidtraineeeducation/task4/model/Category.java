package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

public enum Category {
    CHILDREN(R.string.children),
    ADULTS(R.string.adults),
    ELDERLY(R.string.elderly),
    PETS(R.string.pets),
    EVENTS(R.string.events);

    private final int name;

    Category(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }
}
