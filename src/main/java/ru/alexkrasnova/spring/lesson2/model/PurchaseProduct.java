package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchaseProduct implements Serializable {
    @Column(name = "purchase_id", insertable = false, updatable = false)
    private Long purchase_id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long product_id;
}
