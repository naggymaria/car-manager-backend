package com.example.car_manager.core.controllers;

import com.example.car_manager.core.model.Car;
import com.example.car_manager.core.services.CarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(path = "/{id}")
    public ResponseEntity getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity getCarByCode(@PathVariable String code) {
        return ResponseEntity.ok(carService.getCarByCode(code));
    }

    @PostMapping(path = "/create")
    public ResponseEntity createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.createCar(car));
    }
}
