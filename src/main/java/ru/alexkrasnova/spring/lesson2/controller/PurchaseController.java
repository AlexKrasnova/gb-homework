package ru.alexkrasnova.spring.lesson2.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithCustomerDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithCustomerIdDTO;
import ru.alexkrasnova.spring.lesson2.mapper.PurchaseMapper;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.service.PurchaseService;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;

    @GetMapping
    public List<PurchaseWithCustomerDetailsDTO> findAll() {
        List<Purchase> purchases = purchaseService.findAll();
        return purchases.stream().map(purchaseMapper::convertPurchaseToPurchaseWithCustomerDetailsDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PurchaseWithCustomerDetailsDTO findById(@PathVariable Long id) {
        return purchaseMapper.convertPurchaseToPurchaseWithCustomerDetailsDTO(purchaseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        //purchaseService.deleteById(id);
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PurchaseWithCustomerIdDTO purchaseWithCustomerIdDTO) {
        Long newId = purchaseService.save(purchaseMapper.convertPurchaseWithCustomerIdDTOToPurchase(purchaseWithCustomerIdDTO));
        return ResponseEntity.created(new URI("/purchases/" + newId)).body(null);
    }
}
