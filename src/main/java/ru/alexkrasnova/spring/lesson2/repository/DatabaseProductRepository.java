package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter.OperationType.*;

@Repository
@Primary
@RequiredArgsConstructor
public class DatabaseProductRepository implements ProductRepository {

    private final TransactionExecutor transactionExecutor;

    @Override
    public List<Product> findAll() {
        return transactionExecutor.execute(session -> {
            Query<Product> query = session.createNamedQuery("allProductsSelect",
                    Product.class);
            query.setLockMode(LockModeType.OPTIMISTIC);
            List<Product> products = query.getResultList();

            return products;
        });
    }

    @Override
    public Product findById(Long id) {
        return transactionExecutor.execute(session -> {
            Query<Product> query = session.createNamedQuery("byIdProductSelect",
                    Product.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC);
            Product product = query.getSingleResult();

            return product;
        });
    }

    @Override
    public void save(Product product) {
        transactionExecutor.execute(session -> {
            session.save(product);
            return null;
        });
    }

    @Override
    public void deleteById(Long id) {
        transactionExecutor.execute(session -> {
            Product product = session.createNamedQuery("byIdProductSelect", Product.class)
                    .setParameter("id", id)
                    .setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
                    .getSingleResult();
            session.delete(product);
            return null;
        });
    }

    @Override
    public void updateById(Long id, Product product) {
        transactionExecutor.execute(session -> {
            Query<Product> query = session.createNamedQuery("byIdProductSelect",
                    Product.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            Product updatedProduct = query.getSingleResult();
            updatedProduct.setName(product.getName());
            updatedProduct.setCompany(product.getCompany());
            updatedProduct.setPrice(product.getPrice());
            return null;
        });
    }

    @Override
    public List<Product> findByFilters(List<ProductFilter> productFilters) {
        return transactionExecutor.execute(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            List<Predicate> predicates = new ArrayList<>();
            for (int i = 0; i < productFilters.size(); i++) {
                ProductFilter.OperationType operationType = productFilters.get(i).getOperationType();
                String fieldName = "";
                if (productFilters.get(i).getFieldName().equals("name")) {
                    fieldName = "name";
                } else if (productFilters.get(i).getFieldName().equals("company")) {
                    fieldName = "company";
                } else if (productFilters.get(i).getFieldName().equals("price")) {
                    fieldName = "price";
                }
                if (!fieldName.equals("")) {
                    if (operationType.equals(EQUALS)) {
                        predicates.add(cb.equal(root.get(fieldName), productFilters.get(i).getValue()));
                    }
                    if (operationType.equals(GREATER_THEN)) {
                        predicates.add(cb.gt(root.get(productFilters.get(i).getFieldName()), (Number) productFilters.get(i).getValue()));
                    }
                    if (operationType.equals(LESS_THEN)) {
                        predicates.add(cb.lt(root.get(productFilters.get(i).getFieldName()), (Number) productFilters.get(i).getValue()));
                    }
                    if (operationType.equals(LIKE)) {
                        predicates.add(cb.like(root.get(productFilters.get(i).getFieldName()), (String) productFilters.get(i).getValue()));
                    }
                    if (operationType.equals(NOT_EQUALS)) {
                        predicates.add(cb.notEqual(root.get(productFilters.get(i).getFieldName()), productFilters.get(i).getValue()));
                    }
                }
            }
            cr.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

            Query<Product> query = session.createQuery(cr);
            return query.getResultList();
        });
    }

    /*private <T> T execute(Function<Session, T> work) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                T result = work.apply(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception exception) {
                session.getTransaction().rollback();
                throw exception;
            }
        }
    }*/
}
