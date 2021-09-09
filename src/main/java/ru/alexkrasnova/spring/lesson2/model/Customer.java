package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "allCustomersSelect", query = "select c from Customer c"),
        @NamedQuery(name = "byIdCustomerSelect", query = "select c from Customer c where c.id = :id")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    @Version
    private Integer version;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
