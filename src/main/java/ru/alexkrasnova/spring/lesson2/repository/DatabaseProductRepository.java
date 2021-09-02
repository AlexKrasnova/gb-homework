package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter.OperationType;
import static ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter.OperationType.*;

@Repository
@Primary
@RequiredArgsConstructor
public class DatabaseProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Query<Product> query = session.createNamedQuery("allSelect",
                    Product.class);
            query.setLockMode(LockModeType.OPTIMISTIC);
            List<Product> products = query.getResultList();

            session.getTransaction().commit();

            return products;
        }

    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Query<Product> query = session.createNamedQuery("byIdSelect",
                    Product.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC);
            Product product = query.getSingleResult();

            session.getTransaction().commit();

            return product;
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();

                session.save(product);

                session.getTransaction().commit();
            } catch (Exception exception) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();

                Product product = session.createNamedQuery("byIdSelect", Product.class)
                        .setParameter("id", id)
                        .setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
                        .getSingleResult();
                session.delete(product);

                session.getTransaction().commit();
            } catch (Exception exception) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateById(Long id, Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Product> query = session.createNamedQuery("byIdSelect",
                    Product.class);
            query.setParameter("id", id);
            query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            Product updatedProduct = query.getSingleResult();
            updatedProduct.setName(product.getName());
            updatedProduct.setCompany(product.getCompany());
            updatedProduct.setPrice(product.getPrice());
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Product> findByFilters(ProductFilter[] productFilters) {
        try (Session session = sessionFactory.getCurrentSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);

            List<Predicate> predicates = new ArrayList<>();
            for (int i = 0; i < productFilters.length; i++) {
                ProductFilter.OperationType operationType = productFilters[i].getOperationType();

                if(operationType.equals(EQUALS)) {
                    predicates.add(cb.equal(root.get(productFilters[i].getFieldName()), productFilters[i].getValue()));
                }
                if(operationType.equals(GREATER_THEN)) {
                    predicates.add(cb.gt(root.get(productFilters[i].getFieldName()), (Number) productFilters[i].getValue()));
                }
                if(operationType.equals(LESS_THEN)) {
                    predicates.add(cb.lt(root.get(productFilters[i].getFieldName()), (Number) productFilters[i].getValue()));
                }
                if(operationType.equals(LIKE)) {
                    predicates.add(cb.like(root.get(productFilters[i].getFieldName()),(String) productFilters[i].getValue()));
                }
                if(operationType.equals(NOT_EQUALS)) {
                    predicates.add(cb.notEqual(root.get(productFilters[i].getFieldName()), productFilters[i].getValue()));
                }
              //Почему-то не работает switch, ругается что Constant expression required
/*                switch (operationType) {
                    case (GREATER_THEN):
                        predicates.add(cb.gt(root.get(productFilters[i].getFieldName()), (Number) productFilters[i].getValue()));
                        break;
                    // ...
                    default:
                        break;
                }*/
            }
            cr.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

            Query<Product> query = session.createQuery(cr);
            return query.getResultList();
        }
    }
}
