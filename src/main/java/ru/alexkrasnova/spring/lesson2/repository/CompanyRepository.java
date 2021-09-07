package ru.alexkrasnova.spring.lesson2.repository;

import java.util.List;

public interface CompanyRepository {

    List<String> findAll();

}
