package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String uri;
    private String secondName;
    private String firstName;
    private String birthday;
    private String fieldActivity;
    private String password;
    private String email;
    private String phoneNumber;

    public User() {
    }

    protected User(Parcel in) {
        uri = in.readString();
        secondName = in.readString();
        firstName = in.readString();
        birthday = in.readString();
        fieldActivity = in.readString();
        password = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getPhoto() {
        return uri;
    }

    public void setPhoto(String uri) {
        this.uri = uri;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFieldActivity() {
        return fieldActivity;
    }

    public void setFieldActivity(String fieldActivity) {
        this.fieldActivity = fieldActivity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "photo=" + uri +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", fieldActivity='" + fieldActivity + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
        dest.writeString(secondName);
        dest.writeString(firstName);
        dest.writeString(birthday);
        dest.writeString(fieldActivity);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(phoneNumber);
    }
}
