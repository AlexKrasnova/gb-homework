package ru.alexkrasnova.spring.lesson2.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.mapper.CustomerMapper;
import ru.alexkrasnova.spring.lesson2.mapper.ProductMapper;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.service.CustomerService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Api(produces = "application/json", consumes = "application/json", protocols = "http")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    @ApiOperation("Получение всех покупателей")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден список всех покупателей"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Поиск покупателя по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден покупатель с заданым ID"),
            @ApiResponse(code = 404, message = "Покупатель с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public CustomerDTO findById(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID пользователя"
            )
            @PathVariable Long id) {
        return customerMapper.convertCustomerToCustomerDTO(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление покупателя по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Покупатель с заданым ID удален"),
            @ApiResponse(code = 404, message = "Покупатель с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public void deleteById(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID пользователя"
            )
            @PathVariable Long id) {
        customerService.deleteById(id);
    }

    @SneakyThrows
    @PostMapping
    @ApiOperation("Сохранение нового покупателя")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Новый покупатель успешно добавлен в бд"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public ResponseEntity<Void> save(
            @ApiParam(
                    name = "customer",
                    type = "CustomerDTO",
                    value = "Новый пользователь",
                    required = true
            )
            @RequestBody CustomerDTO customer) {
        Long newId = customerService.save(customerMapper.convertCustomerDTOToCustomer(customer));
        return ResponseEntity.created(new URI("/api/v1/products/" + newId)).body(null);
    }
}
