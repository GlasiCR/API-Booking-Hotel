package com.project.bookingHotel.repositories;

import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findByRoom(Room room);
}
