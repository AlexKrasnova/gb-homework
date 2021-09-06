package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase")
@NamedQueries({
        @NamedQuery(name = "allPurchasesSelect", query = "select p from Purchase p "),
        @NamedQuery(name = "byIdPurchaseSelect", query = "select p from Purchase p where p.id = :id")
})
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private List<PurchaseDetailing> purchaseDetailings;

    @Version
    private Integer version;

    public Purchase(Date date, Long customerId, List<PurchaseDetailing> purchaseDetailings) {
        this.date = date;
        this.customerId = customerId;
        this.purchaseDetailings = purchaseDetailings;
    }

    public Purchase(Long id, Date date, Customer customer, List<PurchaseDetailing> purchaseDetailings) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.purchaseDetailings = purchaseDetailings;
    }
}
