package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

@Repository
@RequiredArgsConstructor
public class DatabasePurchaseDetailingRepository implements PurchaseDetailingRepository {

    private final TransactionExecutor transactionExecutor;

    @Override
    public void save(PurchaseDetailing purchaseDetailing) {
        transactionExecutor.execute(session -> {
            session.save(purchaseDetailing);
            return null;
        });
    }
}
