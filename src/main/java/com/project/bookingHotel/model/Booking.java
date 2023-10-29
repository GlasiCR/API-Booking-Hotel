package com.project.bookingHotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.bookingHotel.enums.StatusBooking;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private LocalDate checkin;
    @Column(nullable = false)
    private LocalDate checkout;
    @Column(nullable = false)
    private Integer priceBooking;
    @Column(nullable = false)
    private Integer daysOfBooking;
    @Column(nullable = false, length = 16)
    private Long numberCreditCard;
    @Enumerated(EnumType.STRING)
    private StatusBooking statusBooking;
    @Column(nullable = false)
    private Integer quantityGuest;
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

    @CreationTimestamp
    @Column(name="createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updatedAt", nullable = false)
    private LocalDateTime updatedAt;
    public Booking(LocalDate checkin, LocalDate checkout, Integer priceBooking, Integer daysOfBooking, Long numberCreditCard, StatusBooking statusBooking) {
        this.checkin = checkin;
        this.checkout = checkout;
        //this.user = user;
        this.priceBooking = priceBooking;
        this.daysOfBooking =  daysOfBooking;
        this.numberCreditCard = numberCreditCard;
        this.statusBooking = statusBooking;

    }
}


