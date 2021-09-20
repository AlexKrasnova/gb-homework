package ru.alexkrasnova.spring.lesson2.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PurchaseProduct implements Serializable {

    private Long purchase;

    private Long productId;
}
