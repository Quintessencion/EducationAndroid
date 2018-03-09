package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

public enum TypeAssistance {
    HELPING_THINGS(R.string.help_with_things),
    BECOME_VOLUNTEER(R.string.volunteering_1),
    PROFESSIONAL_HELP(R.string.prof_help_2),
    HELP_MONEY(R.string.help_with_money);

    private final int descriptionAssistance;

    TypeAssistance(int descriptionAssistance) {
        this.descriptionAssistance = descriptionAssistance;
    }

    public int getDescriptionAssistance() {
        return descriptionAssistance;
    }
}
