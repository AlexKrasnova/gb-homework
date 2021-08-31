package ru.alexkrasnova.spring.lesson2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.mapper.ProductMapper;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> findAll() {
        List<Product> products = productService.findAll();
        return products.stream().map(productMapper::convertProductToProductDTO).collect(Collectors.toList());
    }

    @PostMapping
    public void save(@RequestBody ProductDTO productDTO) {
        productService.save(productMapper.convertProductDTOToProduct(productDTO));
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productMapper.convertProductToProductDTO(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.updateById(id, productMapper.convertProductDTOToProduct(productDTO));
    }
}
