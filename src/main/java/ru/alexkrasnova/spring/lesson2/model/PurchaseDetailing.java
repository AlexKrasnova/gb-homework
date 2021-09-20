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
@IdClass(PurchaseProduct.class)
@Table(name = "purchase_detailing")
public class PurchaseDetailing {

    @Id
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
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
