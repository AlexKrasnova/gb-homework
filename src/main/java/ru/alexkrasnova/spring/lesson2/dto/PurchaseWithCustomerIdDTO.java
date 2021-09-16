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
@ApiModel("Заказ без детализации")
public class PurchaseWithCustomerIdDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("Дата")
    private Date date;

    @ApiModelProperty("ID покупателя")
    private Long customerId;

    @ApiModelProperty("Детали заказа")
    private List<PurchaseDetailingDTO> purchaseDetailingDTOs;

    /*public PurchaseWithCustomerIdDTO(Date date, Long customerId, List<PurchaseDetailingDTO> purchaseDetailingDTOs) {
        this.date = date;
        this.customerId = customerId;
        this.purchaseDetailingDTOs = purchaseDetailingDTOs;
    }*/
}
