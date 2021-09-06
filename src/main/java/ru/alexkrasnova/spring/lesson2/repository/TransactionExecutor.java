package ru.alexkrasnova.spring.lesson2.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TransactionExecutor {

    public final SessionFactory sessionFactory;

    <T> T execute(Function<Session, T> work) {
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
    }
}
