package com.example.car_manager.core.services;

import com.example.car_manager.core.model.Car;
import com.example.car_manager.core.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    public String createCar(Car c) {
        carRepository.save(c);
        return "Car created!";
    }

    public Car getCarByCode(String code) {
        Optional<Car> car = carRepository.findByCode(code);
        return car.orElse(null);
    }
}
