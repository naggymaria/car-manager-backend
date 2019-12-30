package com.example.car_manager.core.repository;

import com.example.car_manager.core.model.CarPerson;
import com.example.car_manager.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarPersonRepository extends JpaRepository<CarPerson, Long> {
    Optional<CarPerson> findByPersonId(Long personId);
}
