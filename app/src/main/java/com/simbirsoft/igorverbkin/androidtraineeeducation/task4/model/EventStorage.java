package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.PROFESSIONAL_HELP;
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
        events.add(new Event("1",
                "Добровольные пожертвования",
                LocalDate.of(2018, JANUARY, 1),
                LocalDate.of(2018, APRIL, 21),
                "«Мозаика счастья»",
                "centr.umka-deti@mail.ru",
                "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (937) 111-52-46", "+7 (937) 512-72-87"},
                "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям. \n\nПри этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
                "www.umka-deti.spb.ru/",
                false,
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBMWP3fdcyeeEblnf8KBMU1U3m0CeyqcgYIN0AiykDoBBz235Cdg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4YUgZ93MyHALDsl0bR5jm0_zl50c6Yw6lxIqNWLICd2mKoI3K", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCKb333hq1iS7MKFcGTNMLl1Yzhx6baJaAPMydZxQhneZMTuVV", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSCmUdWYph-p0IwvaL-4lONV3vI9slokTL25Dx3IDRiajAc666ag", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrIPBRxdDO6SQ7DACKfLDMkLN8dzdFjr0NoOv5Up2L0DZYoYGE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVCqCO5zYgf30Wcj8xyxK9XlMXBkIP_e_QQvdrgdM99B2hxN_H", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQl2ce8-5g8LCRosHKybOxUaZUZvSWWk3NQsG6QX05i_ABP8YVjcw"},
                new Category[]{HELPING_THINGS, HELP_MONEY, PROFESSIONAL_HELP, BECOME_VOLUNTEER},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_YsLw9J-xvWp-PgIR6_OvvqgkDlQDS4tbCDBqLTLVhjw1qrtdyg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGylVbBL9QzqVpSUKN8vyt0AdM9u7cacMeFFdW3VHEQO5U9JN8fg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmItQqdbundOoPT8dQfpreOMDW8kulykLeQjSPh5_z83FV_1YWVg"}));
        events.add(new Event("2",
                "Посещение оздоровительных курортов тайланда",
                LocalDate.of(2018, JANUARY, 31),
                LocalDate.of(2018, MARCH, 31),
                "Благотворительный Фонд В. Потанина",
                "centr.umka-deti@mail.ru",
                "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (937) 199-55-46", "+7 (937) 215-02-07"},
                "Мы публикуем полезные материалы с подробным описанием без лишней 'воды', с рабочими (проверенными) примерами. Основная наша задача - это донести материалы максимально просто и понятно до читателя.",
                "http://www.umka-deti.spb.ru/",
                true,
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDzzL2oaCPjtWjT2zOia21ESFIv3_Kum5ai6mc1PLHy-1cp0sU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmlHGldLf-EbzVeiqQJwKUfFo3gPwCPYia05e_K-_eOj1UyMIs"},
                new Category[]{PROFESSIONAL_HELP, HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN-nGdLRfmmw_5yPeGsVI0aoQcHrU1-3TKcLp_QKqSY1JLw_kCsw", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrd1mle4JKo9fdXX0_xQPXG2HOakn-qE8KMfeC-UlkLEFWItrA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRcyA-7dXNIl5pzUZHsJuwEusPaXN3cZPxH0nMynSxMcgMMKI1yQ"}));
        events.add(new Event("3",
                "Поможем спасти...", LocalDate.of(2018, FEBRUARY, 5), LocalDate.of(2018, MAY, 8),
                "Благотворительный фонд Алины Калашниковой", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (936) 549-70-15"},
                "",
                "http://www.umka-deti.spb.ru/",
                false,
                null,
                new Category[]{HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStnggLRcBMO-dwQnWb1f6myuKQGacHubvMnMQIZdYMwR2x2xOe", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOo3ktKDf779AUr4jlCs3yslLTXqKbyqYPw2gjzZe5f0JOy8zbiA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfu0y31dN8jP0sktckyL7m7tKUg-9p8colV0Zv18z3xQtjjBIL"}));
        events.add(new Event("4",
                "Техническая помощь нуждающимся",
                LocalDate.of(2018, JANUARY, 1),
                LocalDate.of(2018, APRIL, 21),
                "«Во имя жизни»",
                "centr.umka-deti@mail.ru",
                "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (987) 331-72-11", "+7 (937) 412-77-88"},
                "1111111111111111 22222222222 333333 44 5555555 666666666666666 7777777 8 9 000000",
                "http://www.umka-deti.spb.ru/",
                false,
                null,
                new Category[]{HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJOcCojDu4ji-1myfA-bGNZ3RRo6BcicLDVhoqkZMQFmjHkjxG_A"}));
        events.add(new Event("5",
                "Помощь семьям в трудной жизненной ситуации",
                LocalDate.of(2018, JANUARY, 1),
                LocalDate.of(2018, JUNE, 23),
                "«Детские домики»",
                "centr.umka-deti@mail.ru",
                "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (904) 987-65-43"},
                "",
                "http://www.umka-deti.spb.ru/",
                true,
                null,
                new Category[]{PROFESSIONAL_HELP, HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEaWzS-I9eHvcLkNyyYEh6sw95Zb-ohAfzgUgMMZxOak5PkQ_bvA"}));
        events.add(new Event("6", "Танцы без границ", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, MARCH, 9),
                "АНО «СИНЯЯ ПТИЦА»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (937) 312-00-00", "+7 (904) 111-11-11"}, "", "http://www.umka-deti.spb.ru/", true, null,
                new Category[]{PROFESSIONAL_HELP, HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPaNf5HiEM19uR5Fgp0FfEGi_y4XjrBoEmCr5oa462uVbXGzdK"}));
        events.add(new Event("7", "Веселые выходные в больнице", LocalDate.of(2018, MARCH, 4), LocalDate.of(2018, JULY, 17),
                "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"},
                "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям. \n\nПри этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
                "http://www.umka-deti.spb.ru/", true, null,
                new Category[]{PROFESSIONAL_HELP, HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRB0wE1yTqCkAacii3v0TQn66hUup71Qbfk-XheMLFRDTMPBGj4"}));
        events.add(new Event("8", "Веселый детский утренник", LocalDate.of(2018, FEBRUARY, 22), LocalDate.of(2018, APRIL, 21),
                "Аппарель", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (937) 312-22-01", "+7 (904) 122-13-71"}, "", "http://www.umka-deti.spb.ru/", true, null,
                new Category[]{PROFESSIONAL_HELP, HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKfIkC0Rixp_NGb5pTXDTJk6PCjbIUZsrTFo2dVJd1xDaHp33NJA"}));
        events.add(new Event("9", "\"Добрый сок\" для пожилых", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, FEBRUARY, 26),
                "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (987) 031-72-11", "+7 (937) 048-61-82"}, "", "http://www.umka-deti.spb.ru/", false, null,
                new Category[]{HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMvFk_e2Ojq0JiWXCcn9BATOKw-mKMlswfzeAcyn5XgMC9_-wz"}));
        events.add(new Event("10", "Развивающие игры - веселые гонки", LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, MARCH, 10),
                "«Во имя жизни»", "centr.umka-deti@mail.ru", "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                new String[]{"+7 (987) 331-38-11", "+7 (937) 009-77-83"}, "", "http://www.umka-deti.spb.ru/", false, null,
                new Category[]{HELP_MONEY},
                new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVaNc3iWfacjngJ-jTVuSSIyaOuBXuIxNMlLCYMcC02590ZKdpGg"}));
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
