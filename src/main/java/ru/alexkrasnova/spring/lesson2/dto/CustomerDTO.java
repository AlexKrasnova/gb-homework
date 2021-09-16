package ru.alexkrasnova.spring.lesson2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Покупатель")
public class CustomerDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Имя")
    private String name;

    /*public CustomerDTO(String name) {
        this.name = name;
    }*/
}
