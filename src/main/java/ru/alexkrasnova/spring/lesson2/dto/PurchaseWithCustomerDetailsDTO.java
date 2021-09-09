package ru.alexkrasnova.spring.lesson2.dto;

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
public class PurchaseWithCustomerDetailsDTO {

    private Long id;
    private Date date;
    private CustomerDTO customer;
    private List<PurchaseDetailingDTO> purchaseDetailingDTOs;

    public PurchaseWithCustomerDetailsDTO(Date date, CustomerDTO customer, List<PurchaseDetailingDTO> purchaseDetailingDTOs) {
        this.date = date;
        this.customer = customer;
        this.purchaseDetailingDTOs = purchaseDetailingDTOs;
    }
}
