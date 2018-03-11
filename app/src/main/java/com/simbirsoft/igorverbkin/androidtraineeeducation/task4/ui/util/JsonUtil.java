package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.content.Context;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static ArrayList<Event> readEventsFromJsonFile(Context context, List<Category> filter) throws JSONException {
        JSONObject jsonRoot = new JSONObject(readText(context, R.raw.events));
        JSONArray jsonArrayEvents = jsonRoot.getJSONArray("events");

        ArrayList<Event> events = new ArrayList<>(jsonArrayEvents.length());

        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            JSONObject jsonObject = jsonArrayEvents.getJSONObject(i);

            JSONArray jsonArrayCategories = jsonObject.getJSONArray("categories");
            List<Category> categoriesList = new ArrayList<>();
            Category[] categories = new Category[jsonArrayCategories.length()];
            for (int j = 0; j < jsonArrayCategories.length(); j++) {
                categories[j] = Category.valueOf(jsonArrayCategories.getString(j));
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
            event.setEmail(jsonObject.getString("email"));
            event.setAddress(jsonObject.getString("address"));
            event.setPhones(getArrayByName("phones", jsonObject));
            event.setContent(jsonObject.getString("content"));
            event.setWebSite(jsonObject.getString("webSite"));
            event.setEvent(jsonObject.getBoolean("isEvent"));
            event.setContributors(getArrayByName("contributors", jsonObject));
            event.setCategories(categories);
            event.setPhotos(getArrayByName("photos", jsonObject));

            events.add(event);
        }

        return events;
    }

    private static String readText(Context context, int resId) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = context.getResources().openRawResource(resId);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            Logger.d("Error reading json file: " + e.getMessage());
        }
        return sb.toString();
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
