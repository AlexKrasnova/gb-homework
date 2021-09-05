package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "allSelect", query = "select p from Product p "),
        @NamedQuery(name = "byIdSelect", query = "select p from Product p where p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "price")
    private BigDecimal price;

    @Version
    private Integer version;

    @ManyToMany()
    @JoinTable(
            name = "purchase_detailing",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_id")
    )
    private List<Purchase> purchases;

    public Product(Long id, String name, String company, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + company + " - " + price + " руб";
    }

}
