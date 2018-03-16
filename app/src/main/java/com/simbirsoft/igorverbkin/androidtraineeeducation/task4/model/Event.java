package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.threeten.bp.LocalDate;

import java.util.Arrays;

import lombok.Data;

@Data
public class Event implements Parcelable, Cloneable {

    private String id;
    private String eventName;
    private LocalDate start;
    private LocalDate end;
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

    public void setStart(String start) {
        this.start = LocalDate.parse(start);
    }

    public void setEnd(String end) {
        this.end = LocalDate.parse(end);
    }

    protected Event(Parcel in) {
        id = in.readString();
        eventName = in.readString();
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

    public void setDescriptionAssistance(String descriptionAssistance) {
        this.descriptionAssistance = descriptionAssistance;
    }

    public Event clone() throws CloneNotSupportedException {
        return (Event) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null)
            return false;
        if (start != null ? !start.equals(event.start) : event.start != null) return false;
        if (end != null ? !end.equals(event.end) : event.end != null) return false;
        if (fundName != null ? !fundName.equals(event.fundName) : event.fundName != null)
            return false;
        if (address != null ? !address.equals(event.address) : event.address != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(phones, event.phones)) return false;
        if (content != null ? !content.equals(event.content) : event.content != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (email != null ? !email.equals(event.email) : event.email != null) return false;
        if (webSite != null ? !webSite.equals(event.webSite) : event.webSite != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(contributors, event.contributors)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(categoriesHelp, event.categoriesHelp)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(photos, event.photos)) return false;
        return descriptionAssistance != null ? descriptionAssistance.equals(event.descriptionAssistance) : event.descriptionAssistance == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (fundName != null ? fundName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(phones);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(contributors);
        result = 31 * result + Arrays.hashCode(categoriesHelp);
        result = 31 * result + Arrays.hashCode(photos);
        result = 31 * result + (descriptionAssistance != null ? descriptionAssistance.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(eventName);
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
