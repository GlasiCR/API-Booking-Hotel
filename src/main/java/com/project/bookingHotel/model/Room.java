package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.bookingHotel.model.Hotel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameRoom;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer numberOfRooms;
    @Column(nullable = false)
    private Integer numberOfVacantRooms;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Booking> booking;
}
