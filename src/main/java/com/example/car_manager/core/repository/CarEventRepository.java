package com.example.car_manager.core.repository;

import com.example.car_manager.core.model.CarEvent;
import com.example.car_manager.core.model.CarPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarEventRepository extends JpaRepository<CarEvent, Long> {
    Optional<CarEvent> findByEventId(Long eventId);
}
