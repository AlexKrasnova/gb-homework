package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DatabasePurchaseRepository implements PurchaseRepository {

    private final TransactionExecutor transactionExecutor;
    private final CustomerRepository customerRepository;
    private final PurchaseDetailingRepository purchaseDetailingRepository;

    @Override
    public List<Purchase> findAll() {
        return transactionExecutor.execute(session -> {
            Query<Purchase> query = session.createNamedQuery("allPurchasesSelect",
                    Purchase.class);
            query.setLockMode(LockModeType.OPTIMISTIC);
            List<Purchase> purchases = query.getResultList();
            return purchases;
        });
    }

    @Override
    public Purchase findById(Long id) {
        return transactionExecutor.execute(session -> {
            Query<Purchase> query = session.createNamedQuery("byIdPurchaseSelect",
                Purchase.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC);
            Purchase purchase = query.getSingleResult();

            return purchase;
        });
    }

    @Override
    public Long save(Purchase purchase) {
        return transactionExecutor.execute(session -> {
            session.save(purchase);
            return purchase.getId();
        });
    }
}
