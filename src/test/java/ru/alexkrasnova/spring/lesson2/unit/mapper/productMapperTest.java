package ru.alexkrasnova.spring.lesson2.unit.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.mapper.ProductMapper;
import ru.alexkrasnova.spring.lesson2.model.Product;

import java.math.BigDecimal;

@SpringBootTest(classes = ProductMapper.class)
public class productMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void convertProductDTOToProductSuccess() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Молоко");
        productDTO.setCompany("Агуша");
        productDTO.setPrice(BigDecimal.valueOf(100));

        Product product = productMapper.convertProductDTOToProduct(productDTO);

        Assertions.assertThat(product.getId()).isEqualTo(productDTO.getId());
        Assertions.assertThat(product.getName()).isEqualTo(productDTO.getName());
        Assertions.assertThat(product.getCompany()).isEqualTo(productDTO.getCompany());
        Assertions.assertThat(product.getPrice()).isEqualTo(productDTO.getPrice());
    }

    @Test
    public void convertProductToProductDTOSuccess() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Молоко");
        product.setCompany("Агуша");
        product.setPrice(BigDecimal.valueOf(100));

        ProductDTO productDTO = productMapper.convertProductToProductDTO(product);

        Assertions.assertThat(productDTO.getId()).isEqualTo(product.getId());
        Assertions.assertThat(productDTO.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productDTO.getCompany()).isEqualTo(product.getCompany());
        Assertions.assertThat(productDTO.getPrice()).isEqualTo(product.getPrice());
    }
}
