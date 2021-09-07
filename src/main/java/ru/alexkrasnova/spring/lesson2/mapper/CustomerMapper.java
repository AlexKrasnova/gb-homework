package ru.alexkrasnova.spring.lesson2.mapper;

import org.hibernate.validator.constraints.Currency;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.model.Customer;

@Component
public class CustomerMapper {

    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName());
    }

    public CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName());
    }
}
