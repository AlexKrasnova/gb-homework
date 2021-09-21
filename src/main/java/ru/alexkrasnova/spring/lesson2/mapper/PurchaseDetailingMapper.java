package ru.alexkrasnova.spring.lesson2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDetailingDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDetailingWithProductDetailsDTO;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

@Component
@RequiredArgsConstructor
public class PurchaseDetailingMapper {

    private final ProductMapper productMapper;

    public PurchaseDetailing convertPurchaseDetailingDTOToPurchaseDetailing(PurchaseDetailingDTO purchaseDetailingDTO) {
        PurchaseDetailing purchaseDetailing = new PurchaseDetailing();
        purchaseDetailing.setProductId(purchaseDetailingDTO.getProductId());
        purchaseDetailing.setPrice(purchaseDetailingDTO.getPrice());
        purchaseDetailing.setNumber(purchaseDetailingDTO.getNumber());
        return purchaseDetailing;
    }


    public PurchaseDetailingWithProductDetailsDTO convertPurchaseDetailingToPurchaseDetailingWithProductDetailsDTO(PurchaseDetailing purchaseDetailing) {
        return new PurchaseDetailingWithProductDetailsDTO(productMapper.convertProductToProductDTO(purchaseDetailing.getProduct()), purchaseDetailing.getPrice(), purchaseDetailing.getNumber());
    }
}
