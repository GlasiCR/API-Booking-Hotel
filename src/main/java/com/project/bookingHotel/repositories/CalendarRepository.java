package com.project.bookingHotel.repositories;

import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    //public List<Room> findByRoom(Long room);
    List<Calendar> findByRoom(Room room);
}
