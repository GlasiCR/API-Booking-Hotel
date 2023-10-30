package com.project.bookingHotel.repositories;

import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
    List<Room> findByHotelId(Long hotelId);
    Optional<Room> findById(Room room);
    //Room findByCalendar(Room room);
}
