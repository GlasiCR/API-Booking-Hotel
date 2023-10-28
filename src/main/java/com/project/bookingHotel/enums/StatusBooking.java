package com.project.bookingHotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum StatusBooking {
    PAGO("Pago"),
    CANCELADO("Cancelado"),
    VENCIDOUSUFRUIDO("Vencido/Usufruído"),
    VENCIDONAOUSUFRUIDO("Vencido/Não-usufruído");

    private String status;

    StatusBooking(String status){
        this.status = status;
    }
}
