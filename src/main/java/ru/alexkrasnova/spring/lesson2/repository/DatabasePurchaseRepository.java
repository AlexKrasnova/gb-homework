package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.Purchase;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DatabasePurchaseRepository implements PurchaseRepository {

    private final SessionFactory sessionFactory;


    @Override
    public List<Purchase> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Query<Purchase> query = session.createNamedQuery("allPurchasesSelect",
                    Purchase.class);
            query.setLockMode(LockModeType.OPTIMISTIC);
            List<Purchase> purchases = query.getResultList();

            session.getTransaction().commit();

            return purchases;
        }
    }

    @Override
    public Purchase findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Query<Purchase> query = session.createNamedQuery("byIdPurchaseSelect",
                    Purchase.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC);
            Purchase purchase = query.getSingleResult();

            session.getTransaction().commit();

            return purchase;
        }
    }
}
