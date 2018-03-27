package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends RealmObject implements Cloneable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_FUND_NAME = "fundName";
    public static final String FIELD_FUND_CATEGORY = "category";
    public static final String FIELD_FUND_END_DATE = "end";

    @PrimaryKey
    private String id;
    private String eventName;
    private String start;
    private Date end;
    private String fundName;
    private String address;
    private RealmList<String> phones;
    private String content;
    private String email;
    private String webSite;
    private RealmList<String> contributors;
    private String category;
    private RealmList<String> categoriesHelp;
    private RealmList<String> photos;
    private String descriptionAssistance;

    public String[] getPhones() {
        return getArrayString(phones.size(), phones);
    }

    public String[] getContributors() {
        return getArrayString(contributors.size(), contributors);
    }

    public CategoryHelp[] getCategoriesHelp() {
        CategoryHelp[] categories = new CategoryHelp[categoriesHelp.size()];
        for (int i = 0; i < categoriesHelp.size(); i++) {
            categories[i] = CategoryHelp.valueOf(categoriesHelp.get(i));
        }
        return categories;
    }

    public List<CategoryHelp> getCategoriesHelpList() {
        List<CategoryHelp> categories = new ArrayList<>();
        for (String s : categoriesHelp) {
            categories.add(CategoryHelp.valueOf(s));
        }
        return categories;
    }

    public String[] getPhotos() {
        return getArrayString(photos.size(), photos);
    }

    public LocalDate getStart() {
        return LocalDate.parse(start);
    }

//    public LocalDate getEnd() {
//        return LocalDate.parse(end);
//    }

    public Event clone() throws CloneNotSupportedException {
        return (Event) super.clone();
    }

    private String[] getArrayString(int size, RealmList<String> objects) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = objects.get(i);
        }
        return array;
    }
}
