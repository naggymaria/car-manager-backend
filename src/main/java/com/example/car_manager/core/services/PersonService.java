package com.example.car_manager.core.services;

import com.example.car_manager.core.model.Car;
import com.example.car_manager.core.model.CarPerson;
import com.example.car_manager.core.model.Person;
import com.example.car_manager.core.repository.CarPersonRepository;
import com.example.car_manager.core.repository.CarRepository;
import com.example.car_manager.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private CarPersonRepository carPersonRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Person> getPersonByCarId(Long id) {
        List<CarPerson> carPeople = carPersonRepository.findAll();
        List<Person> people = new ArrayList<>();

        carPeople.forEach(carPerson -> {
            if(carPerson.getCarId().equals(id)) {
                Optional<Person> person = personRepository.findById(carPerson.getPersonId());
                person.ifPresent(people::add);
            }
        });

        return people;
    }

    public String removePerson(Long id) {
        Optional<CarPerson> carPerson = carPersonRepository.findByPersonId(id);
        Optional<Person> person = personRepository.findById(id);

        carPerson.ifPresent(value -> carPersonRepository.delete(value));

        if (person.isPresent()) {
            personRepository.delete(person.get());
            return "Person deleted successfully!";
        } else {
            return "Person does not exist!";
        }
    }

    public String createPerson(Person p, String carCode) {
        personRepository.save(p);
        Optional<Car> car = carRepository.findByCode(carCode);
        Optional<Person> person = personRepository.findByCode(p.getCode());

        if(car.isPresent()) {
            CarPerson carPerson = new CarPerson();
            carPerson.setCarId(car.get().getCarId());
            carPerson.setPersonId(person.get().getPersonId());
            carPersonRepository.save(carPerson);
            return "Person created!";
        }

        return "Error during creation!";
    }
}
