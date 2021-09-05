package ru.alexkrasnova.spring.lesson2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDetailingDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithCustomerDetailsDTO;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseDetailingMapper {

    private final ProductMapper productMapper;

    public PurchaseDetailing convertPurchaseDetailingDTOToPurchaseDetailing(PurchaseDetailingDTO purchaseDetailingDTO) {
        return new PurchaseDetailing(productMapper.convertProductDTOToProduct(purchaseDetailingDTO.getProductDTO()), purchaseDetailingDTO.getPrice(), purchaseDetailingDTO.getNumber());
    }


    public PurchaseDetailingDTO convertPurchaseDetailingToPurchaseDetailingDTO(PurchaseDetailing purchaseDetailing) {
        return new PurchaseDetailingDTO(productMapper.convertProductToProductDTO(purchaseDetailing.getProduct()), purchaseDetailing.getPrice(), purchaseDetailing.getNumber());
    }
}
