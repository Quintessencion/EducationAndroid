package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp.PROFESSIONAL_HELP;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Filter {

    private boolean isMoneyHelp;
    private boolean isThingsHelp;
    private boolean isProfHelp;
    private boolean isVolunteer;

    public List<CategoryHelp> getFilter() {
        List<CategoryHelp> categories = new ArrayList<>();
        if (isMoneyHelp()) {
            categories.add(HELP_MONEY);
        }
        if (isThingsHelp()) {
            categories.add(HELPING_THINGS);
        }
        if (isProfHelp()) {
            categories.add(PROFESSIONAL_HELP);
        }
        if (isVolunteer()) {
            categories.add(BECOME_VOLUNTEER);
        }

        return categories;
    }
}
