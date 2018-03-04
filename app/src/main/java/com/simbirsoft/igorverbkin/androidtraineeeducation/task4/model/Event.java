package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import java.util.Date;

public class Event {

    private String id;
    private String eventName;
    private Date start;
    private Date end;
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

    public Event(String id, String eventName, Date start, Date end, String fundName, String email,
                 String address, String[] phones, String content, String webSite, boolean isEvent) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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
}
