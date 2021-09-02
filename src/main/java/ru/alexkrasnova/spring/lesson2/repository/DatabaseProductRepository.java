package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
