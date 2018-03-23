package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util;

import android.content.Context;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonUtil {

    public static ArrayList<Event> readAllEvents(Context context, List<CategoryHelp> filter) throws JSONException {
        JSONObject jsonRoot = new JSONObject(openFile(context));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        ArrayList<Event> events = new ArrayList<>(jsonArrayEvents.length());

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            JSONArray jsonArrayCategories = jsonObject.getJSONArray("categoriesHelp");
            List<CategoryHelp> categoriesList = new ArrayList<>();
            CategoryHelp[] categories = new CategoryHelp[jsonArrayCategories.length()];
            for (int j = 0; j < jsonArrayCategories.length(); j++) {
                categories[j] = CategoryHelp.valueOf(jsonArrayCategories.getString(j));
                categoriesList.add(categories[j]);
            }
            if (!categoriesList.containsAll(filter)) {
                continue;
            }

            Event event = new Event();
            event.setId(jsonObject.getString("id"));
            event.setEventName(jsonObject.getString("eventName"));
            event.setStart(jsonObject.getString("start"));
            event.setEnd(jsonObject.getString("end"));
            event.setFundName(jsonObject.getString("fundName"));
            event.setContent(jsonObject.getString("content"));
            event.setPhotos(getArrayByName("photos", jsonObject));

            events.add(event);
        }

        return events;
    }

    public static Event readEventById(Context context, String id) throws JSONException {
        JSONObject jsonRoot = new JSONObject(openFile(context));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            String eventId = jsonObject.getString("id");
            if (!eventId.equals(id)) {
                continue;
            }

            Event event = new Event();
            event.setId(id);
            event.setEventName(jsonObject.getString("eventName"));
            event.setStart(jsonObject.getString("start"));
            event.setEnd(jsonObject.getString("end"));
            event.setFundName(jsonObject.getString("fundName"));
            event.setEmail(jsonObject.getString("email"));
            event.setAddress(jsonObject.getString("address"));
            event.setPhones(getArrayByName("phones", jsonObject));
            event.setContent(jsonObject.getString("content"));
            event.setWebSite(jsonObject.getString("webSite"));
            event.setContributors(getArrayByName("contributors", jsonObject));
            event.setPhotos(getArrayByName("photos", jsonObject));

            JSONArray jsonArrayCategories = jsonObject.getJSONArray("categoriesHelp");
            CategoryHelp[] categories = new CategoryHelp[jsonArrayCategories.length()];
            for (int j = 0; j < jsonArrayCategories.length(); j++) {
                categories[j] = CategoryHelp.valueOf(jsonArrayCategories.getString(j));
            }
            event.setCategoriesHelp(categories);

            return event;
        }
        return null;
    }

    public static List<Event> readEventByCategory(Context context, Category category) throws JSONException {
        JSONObject jsonRoot = new JSONObject(openFile(context));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        ArrayList<Event> events = new ArrayList<>(jsonArrayEvents.length());

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            Category value = Category.valueOf(jsonObject.getString("category"));
            if (!value.equals(category)) {
                continue;
            }

            Event event = new Event();
            event.setId(jsonObject.getString("id"));
            event.setEventName(jsonObject.getString("eventName"));
            event.setStart(jsonObject.getString("start"));
            event.setEnd(jsonObject.getString("end"));
            event.setFundName(jsonObject.getString("fundName"));
            event.setContent(jsonObject.getString("content"));
            event.setPhotos(getArrayByName("photos", jsonObject));

            events.add(event);
        }
        return events;
    }

    public static List<String> readEventByQuery(Context context, String query) throws JSONException {
        JSONObject jsonRoot = new JSONObject(openFile(context));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        Set<String> nameOrganization = new HashSet<>();

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            String eventName = jsonObject.getString("fundName");
            if (eventName.toLowerCase().contains(query.toLowerCase())) {
                nameOrganization.add(eventName);
            }
        }
        List<String> events = new ArrayList<>();
        events.addAll(nameOrganization);

        return events;
    }

    public static List<Event> readEventByName(Context context, String name) throws JSONException {
        JSONObject jsonRoot = new JSONObject(openFile(context));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        ArrayList<Event> events = new ArrayList<>(jsonArrayEvents.length());

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            String fundName = jsonObject.getString("fundName");
            if (!fundName.equals(name)) {
                continue;
            }

            Event event = new Event();
            event.setId(jsonObject.getString("id"));
            event.setEventName(jsonObject.getString("eventName"));
            event.setStart(jsonObject.getString("start"));
            event.setEnd(jsonObject.getString("end"));
            event.setFundName(fundName);
            event.setContent(jsonObject.getString("content"));
            event.setPhotos(getArrayByName("photos", jsonObject));

            events.add(event);
        }
        return events;
    }

    private static String openFile(Context context) {
        try (InputStream is = context.getAssets().open("events.json")) {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            return new String(buffer);
        } catch (IOException e) {
            Logger.d("Error reading json file: " + e.getMessage());
        }
        return null;
    }

    private static String[] getArrayByName(String nameArray, JSONObject jsonObject) {
        JSONArray jsonArray;
        String[] array = null;
        try {
            jsonArray = jsonObject.getJSONArray(nameArray);
            array = new String[jsonArray.length()];
            for (int j = 0; j < jsonArray.length(); j++) {
                array[j] = jsonArray.getString(j);
            }
        } catch (JSONException e) {
            Logger.d("Error parsing the json file array name = '" + nameArray + "'. " + e.getMessage());
        }
        return array;
    }
}
