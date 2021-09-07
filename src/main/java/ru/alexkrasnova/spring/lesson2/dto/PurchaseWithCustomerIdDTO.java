package ru.alexkrasnova.spring.lesson2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseWithCustomerIdDTO {

    private Long id;
    private Date date;
    private Long customerId;
    private List<PurchaseDetailingDTO> purchaseDetailingDTOs;

    /*public PurchaseWithCustomerIdDTO(Date date, Long customerId, List<PurchaseDetailingDTO> purchaseDetailingDTOs) {
        this.date = date;
        this.customerId = customerId;
        this.purchaseDetailingDTOs = purchaseDetailingDTOs;
    }*/
}
