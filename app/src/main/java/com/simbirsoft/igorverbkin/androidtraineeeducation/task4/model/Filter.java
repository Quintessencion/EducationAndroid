package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.PROFESSIONAL_HELP;

public class Filter implements Parcelable {

    private boolean isVolunteer;
    private boolean isMoneyHelp;
    private boolean isThingsHelp;
    private boolean isProfHelp;

    public List<Category> getFilter() {
        List<Category> categories = new ArrayList<>();
        if (isVolunteer()) {
            categories.add(BECOME_VOLUNTEER);
        }
        if (isMoneyHelp()) {
            categories.add(HELP_MONEY);
        }
        if (isThingsHelp()) {
            categories.add(HELPING_THINGS);
        }
        if (isProfHelp()) {
            categories.add(PROFESSIONAL_HELP);
        }
        return categories;
    }

    public Filter() {
    }

    public Filter(boolean isMoneyHelp, boolean isThingsHelp, boolean isProfHelp, boolean isVolunteer) {
        this.isMoneyHelp = isMoneyHelp;
        this.isThingsHelp = isThingsHelp;
        this.isProfHelp = isProfHelp;
        this.isVolunteer = isVolunteer;
    }

    protected Filter(Parcel in) {
        isVolunteer = in.readByte() != 0;
        isMoneyHelp = in.readByte() != 0;
        isThingsHelp = in.readByte() != 0;
        isProfHelp = in.readByte() != 0;
    }

    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }

    public boolean isMoneyHelp() {
        return isMoneyHelp;
    }

    public void setMoneyHelp(boolean moneyHelp) {
        isMoneyHelp = moneyHelp;
    }

    public boolean isThingsHelp() {
        return isThingsHelp;
    }

    public void setThingsHelp(boolean thingsHelp) {
        isThingsHelp = thingsHelp;
    }

    public boolean isProfHelp() {
        return isProfHelp;
    }

    public void setProfHelp(boolean profHelp) {
        isProfHelp = profHelp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isVolunteer ? 1 : 0));
        dest.writeByte((byte) (isMoneyHelp ? 1 : 0));
        dest.writeByte((byte) (isThingsHelp ? 1 : 0));
        dest.writeByte((byte) (isProfHelp ? 1 : 0));
    }

    public static final Creator<Filter> CREATOR = new Creator<Filter>() {
        @Override
        public Filter createFromParcel(Parcel in) {
            return new Filter(in);
        }

        @Override
        public Filter[] newArray(int size) {
            return new Filter[size];
        }
    };
}
