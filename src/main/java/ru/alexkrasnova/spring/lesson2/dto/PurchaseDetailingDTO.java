package ru.alexkrasnova.spring.lesson2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Детализация покупки одного товара")
public class PurchaseDetailingDTO {

    @ApiModelProperty("ID продукта")
    Long productId;

    @ApiModelProperty("Цена в момент покупки")
    BigDecimal price;

    @ApiModelProperty("Количество товаров данного типа в заказе")
    Integer number;

}