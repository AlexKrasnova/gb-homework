package ru.alexkrasnova.spring.lesson2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.model.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Детализированный заказ")
public class PurchaseWithCustomerDetailsDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("Дата")
    private Date date;

    @ApiModelProperty("Покупатель")
    private CustomerDTO customer;

    @ApiModelProperty("Детали заказа")
    private List<PurchaseDetailingDTO> purchaseDetailingDTOs;

    public PurchaseWithCustomerDetailsDTO(Date date, CustomerDTO customer, List<PurchaseDetailingDTO> purchaseDetailingDTOs) {
        this.date = date;
        this.customer = customer;
        this.purchaseDetailingDTOs = purchaseDetailingDTOs;
    }
}
