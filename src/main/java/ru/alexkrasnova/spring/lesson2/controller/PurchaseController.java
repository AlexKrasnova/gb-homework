package ru.alexkrasnova.spring.lesson2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDTO;
import ru.alexkrasnova.spring.lesson2.mapper.PurchaseMapper;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.service.PurchaseService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/purchases")
@Api(produces = "application/json", consumes = "application/json", protocols = "http")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;

    @GetMapping
    @ApiOperation("Получение всех заказов")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден список всех заказов"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public List<PurchaseWithDetailsDTO> findAll() {
        List<Purchase> purchases = purchaseService.findAll();
        return purchases.stream().map(purchaseMapper::convertPurchaseToPurchaseWithCustomerDetailsDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Поиск заказа по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден заказ с заданым ID"),
            @ApiResponse(code = 404, message = "Заказ с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public PurchaseWithDetailsDTO findById(@PathVariable Long id) {
        return purchaseMapper.convertPurchaseToPurchaseWithCustomerDetailsDTO(purchaseService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление заказа по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Заказ с заданым ID удален"),
            @ApiResponse(code = 404, message = "Заказ с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public void deleteById(@PathVariable Long id) {
        purchaseService.deleteById(id);
    }

    @SneakyThrows
    @PostMapping
    @ApiOperation("Сохранение нового заказа")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Новый заказ успешно добавлен в бд"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public ResponseEntity<Void> save(@RequestBody PurchaseDTO purchaseDTO) {
        Long newId = purchaseService.save(purchaseMapper.convertPurchaseDTOToPurchase(purchaseDTO));
        return ResponseEntity.created(new URI("/api/v1/purchases/" + newId)).body(null);
    }
}
