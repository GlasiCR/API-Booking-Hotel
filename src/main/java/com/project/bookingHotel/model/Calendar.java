package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="calendars")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCheckin;
    private LocalDate dateCheckout;
    private Integer periodBooking;

    /*@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> booking;*/

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_room")
    private Room room;

}
