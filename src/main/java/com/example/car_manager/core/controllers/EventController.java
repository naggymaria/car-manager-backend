package com.example.car_manager.core.controllers;

import com.example.car_manager.core.model.Event;
import com.example.car_manager.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/car/{id}/all")
    public ResponseEntity getAllCarEvents(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getAllCarEvents(id));
    }

    @DeleteMapping(path = "/{id}/remove")
    public ResponseEntity deleteEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.removeEvent(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity createEvent(@RequestBody Event event, @RequestParam String carCode) {
        return ResponseEntity.ok(eventService.createEvent(event, carCode));
    }

    @PutMapping(path = "/{id}/close")
    public ResponseEntity closeEvent(@PathVariable Long id, @RequestParam double expenses, @RequestParam double profit) {
        return ResponseEntity.ok(eventService.closeEvent(id, expenses, profit));
    }
}
