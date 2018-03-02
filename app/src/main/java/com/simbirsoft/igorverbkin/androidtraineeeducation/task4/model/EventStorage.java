package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventStorage {

    private List<Event> events;

    public EventStorage() {
        events = new ArrayList<>();
        fillData();
    }

    private void fillData() {
        events.add(new Event("1", "Виктор Кузьмин", new Date(), new Date(), "«Мозаика счастья»", "centr.umka-deti@mail.ru", "www.umka-deti.spb.ru/", false));
        events.add(new Event("2", "Поможем Лёне встать на ножки", new Date(), new Date(), "Благотворительный фонд Алины Калашниковой", "centr.umka-deti@mail.ru", "http://www.umka-deti.spb.ru/", false));
        events.add(new Event("3", "Маша Малышева", new Date(), new Date(), "«Во имя жизни»", "centr.umka-deti@mail.ru", "http://www.umka-deti.spb.ru/", false));
        events.add(new Event("4", "Посещение детей в НИИ дет. онкол. им Р. М. Горбачевой", new Date(), new Date(), "Благотворительный Фонд В. Потанина", "centr.umka-deti@mail.ru", "http://www.umka-deti.spb.ru/", false));
        events.add(new Event("5", "Помощь семьям в трудной жизненной ситуации", new Date(), new Date(), "«Детские домики»", "centr.umka-deti@mail.ru", "http://www.umka-deti.spb.ru/", true));
        events.add(new Event("6", "Танцы без границ", new Date(), new Date(), "АНО «СИНЯЯ ПТИЦА»", "centr.umka-deti@mail.ru", "http://www.umka-deti.spb.ru/", true));
//        events.add(new Event("7", "Аппарель", new Date()));
//        events.add(new Event("8", "БФ «ПОДАРОК АНГЕЛУ»", new Date()));
//        events.add(new Event("9", "Безопасный дом", new Date()));
//        events.add(new Event("11", "БЛАГО ДАРЮ", new Date()));
//        events.add(new Event("12", "БУМАЖНЫЙ ЖУРАВЛИК", new Date()));
//        events.add(new Event("13", "Благотворительный фонд «Лучик»", new Date()));
//        events.add(new Event("14", "Благотворительный фонд «Система»", new Date()));
//        events.add(new Event("15", "Благотворительный фонд ЦФО", new Date()));
//        events.add(new Event("16", "Благотворительный фонд «Кораблик»", new Date()));
//        events.add(new Event("17", "БФ «Живи»", new Date()));
//        events.add(new Event("18", "Любовь и Ёжики»", new Date()));
    }

    public Event getEventById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> getEvents() {
        Collections.shuffle(events);
        return events;
    }
}
