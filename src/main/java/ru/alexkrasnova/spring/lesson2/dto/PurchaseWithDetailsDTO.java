package ru.alexkrasnova.spring.lesson2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Детализированный заказ")
public class PurchaseWithDetailsDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("Дата")
    private Date date;

    @ApiModelProperty("Покупатель")
    private CustomerDTO customer;

    @ApiModelProperty("Детали заказа")
    private List<PurchaseDetailingWithProductDetailsDTO> purchaseDetailings;

    public PurchaseWithDetailsDTO(Date date, CustomerDTO customer, List<PurchaseDetailingWithProductDetailsDTO> purchaseDetailings) {
        this.date = date;
        this.customer = customer;
        this.purchaseDetailings = purchaseDetailings;
    }
}
