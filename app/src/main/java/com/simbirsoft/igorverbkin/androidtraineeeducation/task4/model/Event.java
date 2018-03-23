package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.threeten.bp.LocalDate;

import java.util.Arrays;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Event extends RealmObject implements Parcelable, Cloneable {

    @PrimaryKey
    private String id;
    private String eventName;
    private String start;
    private String end;
    private String fundName;
    private String address;
    private String[] phones;
    private String content;
    private String email;
    private String webSite;
    private String[] contributors;
    private Category category;
    private CategoryHelp[] categoriesHelp;
    private String[] photos;
    private String descriptionAssistance;

    public Event() {
    }

    protected Event(Parcel in) {
        id = in.readString();
        eventName = in.readString();
        start = in.readString();
        end = in.readString();
        fundName = in.readString();
        address = in.readString();
        phones = in.createStringArray();
        content = in.readString();
        email = in.readString();
        webSite = in.readString();
        contributors = in.createStringArray();
        photos = in.createStringArray();
        descriptionAssistance = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public LocalDate getStart() {
        return LocalDate.parse(start);
    }

    public LocalDate getEnd() {
        return LocalDate.parse(end);
    }

    public Event clone() throws CloneNotSupportedException {
        return (Event) super.clone();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(eventName);
        dest.writeString(start);
        dest.writeString(end);
        dest.writeString(fundName);
        dest.writeString(address);
        dest.writeStringArray(phones);
        dest.writeString(content);
        dest.writeString(email);
        dest.writeString(webSite);
        dest.writeStringArray(contributors);
        dest.writeStringArray(photos);
        dest.writeString(descriptionAssistance);
    }
}
