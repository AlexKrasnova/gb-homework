package ru.alexkrasnova.spring.lesson2.mapper;

import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.model.Product;

@Component
public class ProductMapper {

    public Product convertProductDTOToProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getCompany(), productDTO.getPrice());
    }

    public ProductDTO convertProductToProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getCompany(), product.getPrice());
    }
}
