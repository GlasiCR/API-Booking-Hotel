package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.bookingHotel.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_room")
    private Room room;


    public Booking(LocalDateTime checkin, LocalDateTime checkout, User user) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.user = user;

       /* this.hotel = idHotel;
        this.room = idRoom;*/
    }



   /* public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }*/
}


