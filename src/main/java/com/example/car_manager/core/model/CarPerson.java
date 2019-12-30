package com.example.car_manager.core.model;

import javax.persistence.*;

@Entity
@Table
public class CarPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long carId;
    private Long personId;

    public CarPerson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
