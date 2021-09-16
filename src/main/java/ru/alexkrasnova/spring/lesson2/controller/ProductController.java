package ru.alexkrasnova.spring.lesson2.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.mapper.ProductMapper;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilters;
import ru.alexkrasnova.spring.lesson2.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@Api(produces = "application/json", consumes = "application/json", protocols = "http")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @ApiOperation("Получение всех продуктов")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден список всех продуктов"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public List<ProductDTO> findAll() {
        List<Product> products = productService.findAll();
        return products.stream().map(productMapper::convertProductToProductDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    @PostMapping
    @ApiOperation("Сохранение нового продукта")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Новый продукт успешно добавлен в бд"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public ResponseEntity<Void> save(
            @ApiParam(
                    name = "product",
                    type = "ProductDTO",
                    value = "Новый продукт",
                    required = true
            )
            @Valid @RequestBody ProductDTO product) {
        Long newId = productService.save(productMapper.convertProductDTOToProduct(product));
        return ResponseEntity.created(new URI("/api/v1/products/" + newId)).body(null);
    }

    @GetMapping("/{id}")
    @ApiOperation("Поиск продукта по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден продукт с заданым ID"),
            @ApiResponse(code = 404, message = "Продукт с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public ProductDTO findById(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID продукта"
            )
            @PathVariable Long id) {
        return productMapper.convertProductToProductDTO(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление продукта по id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Продукт с заданым ID удален"),
            @ApiResponse(code = 404, message = "Продукт с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public void deleteById(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID продукта"
            )
            @PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Обновление продукта")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Продукт с заданым ID изменен"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 404, message = "Продукт с заданым ID не найден"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public void updateById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.updateById(id, productMapper.convertProductDTOToProduct(productDTO));
    }

    // Сомневаюсь, стоит ли переделывать в get.
    //
    // Валидацию фильтров удалось сделать только с добавлением новой сущности ProductFilters.
    // Пробовала еще передавать List<@Valid ProductFilters> productFilters, но так не работает.
    // Можно ли как-то по-другому эту пробему решить?
    @PostMapping("/get")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Найден список продуктов по фильтрам"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 500, message = "Неизвестная ошибка на сервере")
    })
    public List<ProductDTO> findByFilters(
            @ApiParam(
                    name = "productFilters",
                    type = "ProductFilters",
                    value = "Фильтры для продукта",
                    required = true
            )
            @RequestBody @Valid ProductFilters productFilters) {
        List<Product> products = productService.findByFilters(productFilters.getProductFilters());
        return products.stream().map(productMapper::convertProductToProductDTO).collect(Collectors.toList());
    }
}
