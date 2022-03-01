package ru.alexkrasnova.spring.lesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexkrasnova.spring.lesson11.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
