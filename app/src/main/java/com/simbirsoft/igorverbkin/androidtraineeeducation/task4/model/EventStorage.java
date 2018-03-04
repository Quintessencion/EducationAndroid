package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventStorage {

    private List<Event> events;

    public EventStorage() {
        events = new ArrayList<>();
        fillData();
    }

    private void fillData() {
        events.add(new Event("1", "Виктор Кузьмин", new Date(1519932857233L), new Date(1528659257233L), "«Мозаика счастья»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 111-52-46", "+7 (937) 512-72-87"}, "11111111111 222222222222222 333333 44444444 55 6 6 77777777777 \n\n88888 9999 00000", "www.umka-deti.spb.ru/", false));
        events.add(new Event("2", "Поможем Лёне встать на ножки", new Date(1520043257233L), new Date(1537731257233L), "Благотворительный фонд Алины Калашниковой", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (936) 549-70-15"}, "", "http://www.umka-deti.spb.ru/", false));
        events.add(new Event("3", "Маша Малышева", new Date(1521920057233L), new Date(1530473657233L), "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (987) 331-72-11", "+7 (937) 412-77-88"}, "", "http://www.umka-deti.spb.ru/", false));
        events.add(new Event("4", "Посещение детей в НИИ дет. онкол. им Р. М. Горбачевой", new Date(1514764800000L), new Date(1519932857233L), "Благотворительный Фонд В. Потанина", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 199-55-46", "+7 (937) 215-02-07"}, "", "http://www.umka-deti.spb.ru/", true));
        events.add(new Event("5", "Помощь семьям в трудной жизненной ситуации", new Date(1516425600000L), new Date(1539561600000L), "«Детские домики»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (904) 987-65-43"}, "", "http://www.umka-deti.spb.ru/", true));
        events.add(new Event("6", "Танцы без границ", new Date(1519689600000L), new Date(1522800000000L), "АНО «СИНЯЯ ПТИЦА»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-00-00", "+7 (904) 111-11-11"}, "", "http://www.umka-deti.spb.ru/", true));
        events.add(new Event("7", "Веселые выходные в больнице", new Date(1519639100000L), new Date(1522800000000L), "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"}, "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям. \n\nПри этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.", "http://www.umka-deti.spb.ru/", true));
        events.add(new Event("8", "\"Добрый сок\" для пожилых", new Date(1519611100000L), new Date(1522900000000L), "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208", new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"}, "", "http://www.umka-deti.spb.ru/", true));
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

    public List<String> getOrganizationsByNameRequest(String fundName) {
        Set<String> nkoEvents = new HashSet<>();
        for (Event event : events) {
            if (event.getFundName().toLowerCase().contains(fundName.toLowerCase())) {
                nkoEvents.add(event.getFundName());
            }
        }
        List<String> events = new ArrayList<>();
        events.addAll(nkoEvents);
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
