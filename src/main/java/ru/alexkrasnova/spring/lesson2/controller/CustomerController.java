package ru.alexkrasnova.spring.lesson2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.mapper.CustomerMapper;
import ru.alexkrasnova.spring.lesson2.mapper.ProductMapper;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable Long id) {
        return customerMapper.convertCustomerToCustomerDTO(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerMapper.convertCustomerDTOToCustomer(customerDTO));
    }
}
