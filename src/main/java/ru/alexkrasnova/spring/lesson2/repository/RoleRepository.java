package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexkrasnova.spring.lesson2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}