package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="calendars")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate dateCheckin;
    @Column(nullable = false)
    private LocalDate dateCheckout;
    @Column(nullable = false)
    private Integer periodBooking;
    @Column(nullable = false)
    private UUID numberBooking;

    /*@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> booking;*/

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_room")
    private Room room;
    /*@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    private Room room;*/

    public Calendar(LocalDate checkin, LocalDate checkout, Integer daysBooking, UUID id, Room roomBooking) {
        this.dateCheckin = checkin;
        this.dateCheckout = checkout;
        this.periodBooking = daysBooking;
        this.numberBooking = id;
        this.room = roomBooking;
    }
}
