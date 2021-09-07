package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.model.Product;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DatabaseCustomerRepository implements CustomerRepository {

    private final TransactionExecutor transactionExecutor;

    @Override
    public List<Customer> findAll() {
        return transactionExecutor.execute(session -> {
            Query<Customer> query = session.createNamedQuery("allCustomersSelect",
                    Customer.class);
            query.setLockMode(LockModeType.OPTIMISTIC);
            List<Customer> customers = query.getResultList();

            return customers;
        });
    }

    @Override
    public Customer findById(Long id) {
        return transactionExecutor.execute(session -> {
            Query<Customer> query = session.createNamedQuery("byIdCustomerSelect",
                    Customer.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC);

            return query.getSingleResult();
        });
    }

    @Override
    public void deleteById(Long id) {
        transactionExecutor.execute(session -> {
            Customer customer = session.createNamedQuery("byIdCustomerSelect",
                    Customer.class)
                    .setParameter("id", id)
                    .setLockMode(LockModeType.OPTIMISTIC).getSingleResult();
            session.delete(customer);
            return null;
        });
    }

    @Override
    public void save(Customer customer) {
        transactionExecutor.execute(session -> {
            session.save(customer);
            return null;
        });
    }


}
