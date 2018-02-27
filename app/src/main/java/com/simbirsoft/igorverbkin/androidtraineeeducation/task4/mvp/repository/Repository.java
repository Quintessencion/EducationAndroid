package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.WantHelp;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;

import java.util.ArrayList;
import java.util.List;

public class Repository {

//    private static Repository INSTANCE = new Repository();
    private List<NkoEvent> nkos;

    public Repository() {
        WantHelp.getComponent().inject(this);
    }

//    public static Repository newInstance() {
//        return INSTANCE;
//    }

    public List<NkoEvent> getData() {
        if (nkos != null && !nkos.isEmpty()) {
            return nkos;
        }

        nkos = new ArrayList<>();
        nkos.add(new NkoEvent("Благотворительный фонд Алины Калашниковой"));
        nkos.add(new NkoEvent("«Во имя жизни»"));
        nkos.add(new NkoEvent("Благотворительный Фонд В. Потанина"));
        nkos.add(new NkoEvent("«Детские домики»"));
        nkos.add(new NkoEvent("«Мозаика счастья»"));
        nkos.add(new NkoEvent("АНО «СИНЯЯ ПТИЦА»"));
        nkos.add(new NkoEvent("Аппарель"));
        nkos.add(new NkoEvent("БФ «ПОДАРОК АНГЕЛУ»"));
        nkos.add(new NkoEvent("Безорасный дом"));
        nkos.add(new NkoEvent("БЛАГО ДАРЮ"));
        nkos.add(new NkoEvent("БУМАЖНЫЙ ЖУРАВЛИК"));
        nkos.add(new NkoEvent("Благотворительный фонд «Лучик»"));
        nkos.add(new NkoEvent("Благотворительный фонд «Система»"));
        nkos.add(new NkoEvent("Благотворительный фонд ЦФО"));
        nkos.add(new NkoEvent("Благотворительный фонд «Кораблик»"));
        nkos.add(new NkoEvent("БФ «Живи»"));
        nkos.add(new NkoEvent("Любовь и Ёжики»"));

        return nkos;
    }
}
