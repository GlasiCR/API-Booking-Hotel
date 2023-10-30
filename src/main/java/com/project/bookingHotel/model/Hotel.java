package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="hotels")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String descriptionHotel;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> bookingOdHotel;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Integer numberOfRooms;
    @Column(nullable = false)
    private Integer numberOfRoomsAvailable;
    @Column
    private Boolean thereIsRoomsAvailable = true;

    @CreationTimestamp
    @Column(name="createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    public Hotel(String name, String s, String city, Integer integer, Integer integer1) {
        this.name = name;
        this.descriptionHotel = s;
        this.city = city;
        this.numberOfRooms = integer;
        this.numberOfRoomsAvailable = integer1;

    }

    public void addBookingInHotel(Booking booking) {
        if (bookingOdHotel == null) {
            bookingOdHotel = new ArrayList<>();
        }
        bookingOdHotel.add(booking);
        booking.setHotel(this);
    }
}

