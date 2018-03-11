package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.threeten.bp.LocalDate;

import java.util.Arrays;

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
    private boolean isEvent;
    private String[] contributors;
    private Category[] categories;
    private String[] photos;
    private String descriptionAssistance;

    public Event() {

    }

    public Event(String id, String eventName, LocalDate start, LocalDate end, String fundName,
                 String email, String address, String[] phones, String content, String webSite,
                 boolean isEvent, String[] contributors, Category[] categories, String[] photos) {
        this.id = id;
        this.eventName = eventName;
        this.start = start;
        this.end = end;
        this.address = address;
        this.fundName = fundName;
        this.email = email;
        this.phones = phones;
        this.content = content;
        this.webSite = webSite;
        this.isEvent = isEvent;
        this.contributors = contributors;
        this.categories = categories;
        this.photos = photos;
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
        isEvent = in.readByte() != 0;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = LocalDate.parse(start);
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = LocalDate.parse(end);
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public String[] getContributors() {
        return contributors;
    }

    public void setContributors(String[] contributors) {
        this.contributors = contributors;
    }

    public Category[] getTypesAssistance() {
        return categories;
    }

    public void setTypesAssistance(Category[] categories) {
        this.categories = categories;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getDescriptionAssistance() {
        return descriptionAssistance;
    }

    public void setDescriptionAssistance(String descriptionAssistance) {
        this.descriptionAssistance = descriptionAssistance;
    }

    public Event clone() throws CloneNotSupportedException {
        return (Event)super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (isEvent != event.isEvent) return false;
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
        if (!Arrays.equals(categories, event.categories)) return false;
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
        result = 31 * result + (isEvent ? 1 : 0);
        result = 31 * result + Arrays.hashCode(contributors);
        result = 31 * result + Arrays.hashCode(categories);
        result = 31 * result + Arrays.hashCode(photos);
        result = 31 * result + (descriptionAssistance != null ? descriptionAssistance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", fundName='" + fundName + '\'' +
                ", address='" + address + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", webSite='" + webSite + '\'' +
                ", isEvent=" + isEvent +
                ", contributors=" + Arrays.toString(contributors) +
                ", categories=" + Arrays.toString(categories) +
                ", photos=" + Arrays.toString(photos) +
                ", descriptionAssistance='" + descriptionAssistance + '\'' +
                '}' + "\n";
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
        dest.writeByte((byte) (isEvent ? 1 : 0));
        dest.writeStringArray(contributors);
        dest.writeStringArray(photos);
        dest.writeString(descriptionAssistance);
    }
}
