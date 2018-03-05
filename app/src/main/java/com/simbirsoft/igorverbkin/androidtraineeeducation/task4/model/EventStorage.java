package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.PROFESSIONAL_HELP;
import static org.threeten.bp.Month.APRIL;
import static org.threeten.bp.Month.FEBRUARY;
import static org.threeten.bp.Month.JANUARY;
import static org.threeten.bp.Month.JULY;
import static org.threeten.bp.Month.JUNE;
import static org.threeten.bp.Month.MARCH;
import static org.threeten.bp.Month.MAY;

public class EventStorage {

    private List<Event> events;

    public EventStorage() {
        events = new ArrayList<>();
        fillData();
    }

    private void fillData() {
        events.add(new Event("1", "Виктор Кузьмин", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, APRIL, 21), "«Мозаика счастья»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 111-52-46", "+7 (937) 512-72-87"}, "11111111111 222222222222222 333333 44444444 55 6 6 77777777777 \n\n88888 9999 00000", "www.umka-deti.spb.ru/", false, new int[]{R.drawable.photo_2, R.drawable.photo_4}, new TypeAssistance[]{HELPING_THINGS, HELP_MONEY}));
        events.add(new Event("4", "Посещение детей в НИИ дет. онкол. им Р. М. Горбачевой", LocalDate.of(2018, JANUARY, 31), LocalDate.of(2018, MARCH, 31), "Благотворительный Фонд В. Потанина", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 199-55-46", "+7 (937) 215-02-07"}, "", "http://www.umka-deti.spb.ru/", true, new int[]{R.drawable.photo_2, R.drawable.photo_4, R.drawable.photo_1}, new TypeAssistance[]{PROFESSIONAL_HELP, HELP_MONEY}));
        events.add(new Event("2", "Поможем Лёне встать на ножки", LocalDate.of(2018, FEBRUARY, 5), LocalDate.of(2018, MAY, 8), "Благотворительный фонд Алины Калашниковой", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (936) 549-70-15"}, "", "http://www.umka-deti.spb.ru/", false, new int[]{R.drawable.photo_2, R.drawable.photo_3}, new TypeAssistance[]{HELP_MONEY}));
        events.add(new Event("3", "Маша Малышева", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, APRIL, 21), "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (987) 331-72-11", "+7 (937) 412-77-88"}, "", "http://www.umka-deti.spb.ru/", false, new int[]{}, new TypeAssistance[]{HELP_MONEY}));
        events.add(new Event("5", "Помощь семьям в трудной жизненной ситуации", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, JUNE, 23), "«Детские домики»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (904) 987-65-43"}, "", "http://www.umka-deti.spb.ru/", true, new int[]{R.drawable.photo_2, R.drawable.photo_4, R.drawable.photo_1}, new TypeAssistance[]{PROFESSIONAL_HELP, HELP_MONEY}));
        events.add(new Event("6", "Танцы без границ", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, MARCH, 9), "АНО «СИНЯЯ ПТИЦА»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-00-00", "+7 (904) 111-11-11"}, "", "http://www.umka-deti.spb.ru/", true, new int[]{R.drawable.photo_2, R.drawable.photo_4}, new TypeAssistance[]{PROFESSIONAL_HELP, HELP_MONEY}));
        events.add(new Event("7", "Веселые выходные в больнице", LocalDate.of(2018, MARCH, 4), LocalDate.of(2018, JULY, 17), "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"}, "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям. \n\nПри этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.", "http://www.umka-deti.spb.ru/", true, new int[]{R.drawable.photo_2, R.drawable.photo_4}, new TypeAssistance[]{PROFESSIONAL_HELP, HELP_MONEY}));
        events.add(new Event("8", "\"Добрый сок\" для пожилых", LocalDate.of(2018, FEBRUARY, 22), LocalDate.of(2018, APRIL, 21), "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"}, "", "http://www.umka-deti.spb.ru/", true, new int[]{R.drawable.photo_2, R.drawable.photo_4}, new TypeAssistance[]{PROFESSIONAL_HELP, HELP_MONEY}));
        events.add(new Event("9", "Андрей Комынов", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, FEBRUARY, 26), "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (987) 031-72-11", "+7 (937) 048-61-82"}, "", "http://www.umka-deti.spb.ru/", false, new int[]{R.drawable.photo_3, R.drawable.photo_4, R.drawable.photo_1, R.drawable.photo_2}, new TypeAssistance[]{HELP_MONEY}));
        events.add(new Event("10", "Евгений Думин", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, MARCH, 10), "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (987) 331-38-11", "+7 (937) 009-77-83"}, "", "http://www.umka-deti.spb.ru/", false, new int[]{R.drawable.photo_2, R.drawable.photo_4}, new TypeAssistance[]{HELP_MONEY}));
    }

    public Event getEventById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public List<String> getOrganizationsByNameRequest(String fundName) {
        Set<String> nkoEvents = new HashSet<>();
        for (Event event : events) {
            if (event.getFundName().toLowerCase().contains(fundName.toLowerCase())) {
                nkoEvents.add(event.getFundName());
            }
        }
        List<String> events = new ArrayList<>();
        events.addAll(nkoEvents);
        Collections.shuffle(events);
        return events;
    }

    public List<Event> getEventsByNameOrganization(String fundName) {
        List<Event> eventsOrganization = new ArrayList<>();
        for (Event event : events) {
            if (event.getFundName().toLowerCase().contains(fundName.toLowerCase())) {
                eventsOrganization.add(event);
            }
        }
        return eventsOrganization;
    }

    public List<Event> getAllEvents() {
        return events;
    }
}
