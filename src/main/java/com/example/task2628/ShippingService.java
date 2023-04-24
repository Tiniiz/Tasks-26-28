package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository rep;

    private final Random rnd = new Random();

    public List<Cargo> ListAll(String keyword, String sort_by, String reverse) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null && sort_by != null) {
            return reverse != null ? rep.sortedSearchDESC(keyword,
                    Cargo.numFields().indexOf(sort_by) + 1) : rep.sortedSearchASC(keyword,
                    Cargo.numFields().indexOf(sort_by) + 1);
        } else if (keyword != null)
            return rep.search(keyword);
        else if (sort_by != null)
            return rep.findAll(Sort.by(reverse != null ? Sort.Direction.DESC : Sort.Direction.ASC, sort_by));

        return rep.findAll();
    }

    public void add(Cargo cargo) {
        rep.save(cargo);
    }

    public void fillN(int n) {

        String[] name = {
                "Пшеница",
                "Рожь",
                "Овес",
                "Ячмень",
                "Зерно кукурузы",
                "Початки кукурузы",
                "Рис нешелушеный",
                "Рис шелушеный (неполированный рис)",
                "Прочие зерновые",
                "Гречиха",
                "Зерно бобов",
                "Зерно гороха",
                "Зерно фасоли",
                "Нут",
                "Полба",
                "Просо",
                "Солод в зерне"
        };

        String[] cities = {"Москва", "Астрахань", "Новгород", "Донецк", "Красноярск", "Калуга", "Рязань", "Сочи", "Мурманск", "Калиниград"};

        List<Cargo> all = new ArrayList<>();

        for (int i = 0; i < n; i ++) {

            Cargo cargo = new Cargo();
            cargo.setName(name[rnd.nextInt(0, name.length)]);
            cargo.setContent(rnd.nextInt(100, 500) + " кг");
            cargo.setCitySend(cities[rnd.nextInt(0, cities.length)]);
            cargo.setDateSend("2023-03-%02d".formatted(rnd.nextInt(1, 32)));
            cargo.setCityArrive(cities[rnd.nextInt(0, cities.length)]);
            cargo.setDateArrive("2023-04-%02d".formatted(rnd.nextInt(1, 32)));

            all.add(cargo);
        }

        rep.saveAll(all);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Cargo get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }
}
