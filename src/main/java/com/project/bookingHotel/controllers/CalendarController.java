package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.ResearchAvailableCalendarDto;
import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.services.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

  @GetMapping("")
    public List<Room> filterByAvailableCalendar(@RequestParam LocalDate checkin, @RequestParam LocalDate checkout, @RequestBody ResearchAvailableCalendarDto ResearchAvailableCalendar){
        return calendarService.filterByAvailableCalendar(checkin, checkout, ResearchAvailableCalendar);
  }
}
