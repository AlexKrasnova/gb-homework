package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class SimpleCompanyRepository  implements CompanyRepository {

    List<String> companies;

    @Override
    public List<String> findAll() {
        return companies;
    }

    @PostConstruct
    public void init() {
        companies = new CopyOnWriteArrayList<>();
        companies.add("Домик в деревне");
        companies.add("Простоквашино");
        companies.add("Valio");
        companies.add("Агуша");
        companies.add("Parlamat");
        companies.add("Ламбер");
        companies.add("Лианозовское");
    }
}
