package ru.alexkrasnova.spring.lesson2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.alexkrasnova.spring.lesson2.dto.deserialization.ProductDeserializer;
import ru.alexkrasnova.spring.lesson2.dto.validation.Company;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Продукт")
public class ProductDTO {

    @ApiModelProperty("ID")
    private Long id;

    @NotNull
    @ApiModelProperty("Название")
    private String name;

    @Company
    @ApiModelProperty("Производитель")
    private String company;

    @ApiModelProperty("Цена")
    private BigDecimal price;

    public ProductDTO(String name, String company, BigDecimal price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }

    public ProductDTO(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + ", " + company + " - " + price + " руб";
    }

}
