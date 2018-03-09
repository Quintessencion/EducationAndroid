package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import org.threeten.bp.LocalDate;

import java.util.Arrays;

public class Event implements Cloneable {

    private String id;
    private String eventName;
    private LocalDate start;
    private LocalDate end;
    private String fundName;
    private String address;
    private String[] phones;
    private String content;
    private String[] friendsId;
    private String email;
    private String webSite;
    private boolean isEvent;
    private String[] contributors;
    private TypeAssistance[] typesAssistance;
    private String[] photos;
    private String descriptionAssistance;

    public Event(String id, String eventName, LocalDate start, LocalDate end, String fundName,
                 String email, String address, String[] phones, String content, String webSite,
                 boolean isEvent, String[] contributors, TypeAssistance[] typesAssistance, String[] photos) {
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
        this.typesAssistance = typesAssistance;
        this.photos = photos;
    }

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

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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

    public String[] getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String[] friendsId) {
        this.friendsId = friendsId;
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

    public TypeAssistance[] getTypesAssistance() {
        return typesAssistance;
    }

    public void setTypesAssistance(TypeAssistance[] typesAssistance) {
        this.typesAssistance = typesAssistance;
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
        if (!Arrays.equals(friendsId, event.friendsId)) return false;
        if (email != null ? !email.equals(event.email) : event.email != null) return false;
        if (webSite != null ? !webSite.equals(event.webSite) : event.webSite != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(contributors, event.contributors)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(typesAssistance, event.typesAssistance)) return false;
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
        result = 31 * result + Arrays.hashCode(friendsId);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (isEvent ? 1 : 0);
        result = 31 * result + Arrays.hashCode(contributors);
        result = 31 * result + Arrays.hashCode(typesAssistance);
        result = 31 * result + Arrays.hashCode(photos);
        result = 31 * result + (descriptionAssistance != null ? descriptionAssistance.hashCode() : 0);
        return result;
    }
}
