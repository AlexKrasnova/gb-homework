package ru.alexkrasnova.spring.lesson2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithCustomerDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithCustomerIdDTO;
import ru.alexkrasnova.spring.lesson2.model.Purchase;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseMapper {

    private final CustomerMapper customerMapper;
    private final PurchaseDetailingMapper purchaseDetailingMapper;

    public Purchase convertPurchaseWithCustomerIdDTOToPurchase(PurchaseWithCustomerIdDTO purchaseDTO) {
        return new Purchase(
                purchaseDTO.getDate(),
                purchaseDTO.getCustomerId(),
                purchaseDTO.getPurchaseDetailingDTOs()
                        .stream()
                        .map(purchaseDetailingMapper::convertPurchaseDetailingDTOToPurchaseDetailing)
                        .collect(Collectors.toList()));
    }

    public PurchaseWithCustomerDetailsDTO convertPurchaseToPurchaseWithCustomerDetailsDTO(Purchase purchase) {
        return new PurchaseWithCustomerDetailsDTO(
                purchase.getId(),
                purchase.getDate(),
                customerMapper.convertCustomerToCustomerDTO(purchase.getCustomer()),
                purchase.getPurchaseDetailings()
                        .stream()
                        .map(purchaseDetailingMapper::convertPurchaseDetailingToPurchaseDetailingDTO)
                        .collect(Collectors.toList()));
    }
}
