package ru.alexkrasnova.spring.lesson2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDetailingDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDTO;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseMapper {

    private final CustomerMapper customerMapper;
    private final PurchaseDetailingMapper purchaseDetailingMapper;

    public Purchase convertPurchaseDTOToPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        purchase.setCustomerId(purchaseDTO.getCustomerId());
        purchase.setDate(purchaseDTO.getDate());
        purchase.setPurchaseDetailings(purchaseDTO.getPurchaseDetailingDTOs()
                .stream()
                .map(new Function<PurchaseDetailingDTO, PurchaseDetailing>() {
                    @Override
                    public PurchaseDetailing apply(PurchaseDetailingDTO purchaseDetailingDTO) {
                        PurchaseDetailing purchaseDetailing = purchaseDetailingMapper.convertPurchaseDetailingDTOToPurchaseDetailing(purchaseDetailingDTO);
                        purchaseDetailing.setPurchase(purchase);
                        return purchaseDetailing;
                    }
                })
                .collect(Collectors.toList()));
        return purchase;
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
