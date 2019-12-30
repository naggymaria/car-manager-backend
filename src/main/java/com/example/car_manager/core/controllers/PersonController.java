package com.example.car_manager.core.controllers;

import com.example.car_manager.core.model.Person;
import com.example.car_manager.core.services.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(path = "/car/{id}/all")
    public ResponseEntity getPersonsByCar(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonByCarId(id));
    }

    @DeleteMapping(path = "/{id}/remove")
    public ResponseEntity removePersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.removePerson(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity createPerson(@RequestBody Person person, @RequestParam String carCode) {
        return ResponseEntity.ok(personService.createPerson(person, carCode));
    }
}
