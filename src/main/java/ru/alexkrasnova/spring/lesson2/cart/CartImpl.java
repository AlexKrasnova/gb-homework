package ru.alexkrasnova.spring.lesson2.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.exception.NoSuchProductException;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CartImpl implements Cart {

    private ProductRepository productRepository;
    private Map<String, Integer> products;

    @Autowired
    public CartImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        products = new HashMap<>();
    }

    @Override
    public void removeProductById(String id) {
        if (!products.containsKey(id)) {
            return;
        }
        if (products.get(id) == 1) {
            products.remove(id);
        } else if (products.get(id) > 1) {
            products.put(id, products.get(id) - 1);
        }
    }

    @Override
    public void addProductById(String id) throws NoSuchProductException {
        Product product = productRepository.getProductById(id);
        if (products.containsKey(product.getId())) {
            products.put(product.getId(), products.get(product.getId()) + 1);
        } else {
            products.put(product.getId(), 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (products.size() > 0) {
            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                stringBuilder.append(productRepository.getProductById(entry.getKey())).append(" - ").append(entry.getValue()).append(" шт\n");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
