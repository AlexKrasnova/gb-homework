package ru.alexkrasnova.spring.lesson2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;

    public CustomerDTO(String name) {
        this.name = name;
    }
}
