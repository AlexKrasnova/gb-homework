package ru.alexkrasnova.spring.lesson11.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson11.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.score = u.score + 1 where u.username = :username")
    void incrementScoreByUsername(@Param("username") String username);
}
