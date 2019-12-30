package com.example.car_manager.core.repository;

import com.example.car_manager.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCode(String code);
}
