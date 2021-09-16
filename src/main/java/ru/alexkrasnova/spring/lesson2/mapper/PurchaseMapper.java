package ru.alexkrasnova.spring.lesson2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDTO;
import ru.alexkrasnova.spring.lesson2.model.Purchase;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseMapper {

    private final CustomerMapper customerMapper;
    private final PurchaseDetailingMapper purchaseDetailingMapper;

    public Purchase convertPurchaseDTOToPurchase(PurchaseDTO purchaseDTO) {
        return new Purchase(
                purchaseDTO.getDate(),
                purchaseDTO.getCustomerId(),
                purchaseDTO.getPurchaseDetailingDTOs()
                        .stream()
                        .map(purchaseDetailingMapper::convertPurchaseDetailingDTOToPurchaseDetailing)
                        .collect(Collectors.toList()));
    }

    public PurchaseWithDetailsDTO convertPurchaseToPurchaseWithCustomerDetailsDTO(Purchase purchase) {
        return new PurchaseWithDetailsDTO(
                purchase.getId(),
                purchase.getDate(),
                customerMapper.convertCustomerToCustomerDTO(purchase.getCustomer()),
                purchase.getPurchaseDetailings()
                        .stream()
                        .map(purchaseDetailingMapper::convertPurchaseDetailingToPurchaseDetailingWithProductDetailsDTO)
                        .collect(Collectors.toList()));
    }
}
