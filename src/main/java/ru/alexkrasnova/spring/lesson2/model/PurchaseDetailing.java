package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_detailing")
public class PurchaseDetailing {

    @EmbeddedId
    private PurchaseProduct purchaseProduct;

    @ManyToOne
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "number")
    private Integer number;

    @Version
    private Integer version;

    public PurchaseDetailing(Product product, BigDecimal price, Integer number) {
        this.product = product;
        this.price = price;
        this.number = number;
    }
}
