package com.example.car_manager.core.services;

import com.example.car_manager.core.model.*;
import com.example.car_manager.core.repository.CarEventRepository;
import com.example.car_manager.core.repository.CarRepository;
import com.example.car_manager.core.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private CarEventRepository carEventRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllCarEvents(Long id) {
        List<CarEvent> carEvents = carEventRepository.findAll();
        List<Event> events = new ArrayList<>();

        carEvents.forEach(carEvent -> {
            if(carEvent.getCarId().equals(id)) {
                Optional<Event> event = eventRepository.findById(carEvent.getEventId());
                event.ifPresent(events::add);
            }
        });

        return events;
    }

    public String removeEvent(Long id) {
        Optional<CarEvent> carEvent = carEventRepository.findByEventId(id);
        Optional<Event> event = eventRepository.findById(id);

        carEvent.ifPresent(value -> carEventRepository.delete(value));

        if (event.isPresent()) {
            eventRepository.delete(event.get());
            return "Event deleted successfully!";
        } else {
            return "Event does not exist!";
        }
    }

    public String createEvent(Event e, String carCode) {
        eventRepository.save(e);

        Optional<Car> car = carRepository.findByCode(carCode);
        Optional<Event> event = eventRepository.findByEventName(e.getEventName());

        if(car.isPresent()) {
            CarEvent carEvent = new CarEvent();
            carEvent.setCarId(car.get().getCarId());
            carEvent.setEventId(event.get().getEventId());
            carEventRepository.save(carEvent);
            return "Event created!";
        }

        return "Error during creation!";
    }

    public String closeEvent(Long id, double expenses, double profit) {
        Optional<Event> event = eventRepository.findById(id);
        Optional<CarEvent> carEvent = carEventRepository.findByEventId(id);
        Optional<Car> car = Optional.empty();

        if(carEvent.isPresent())
            car = carRepository.findById(carEvent.get().getCarId());

        if(event.isPresent() && car.isPresent()) {
            event.get().setDone(true);
            event.get().setExpenses(expenses);
            event.get().setRevenue(profit);
            eventRepository.save(event.get());

            car.get().setAmount(car.get().getAmount() + (profit - expenses));
            carRepository.save(car.get());

            return "Event closed and amount updated!";
        } else {
            return "Error editing event!";
        }
    }
}
