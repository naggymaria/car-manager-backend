package com.example.car_manager.core.repository;

import com.example.car_manager.core.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByCode(String code);
}
