package com.project.bookingHotel.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public void setStatus(String status) {
        this.status = status;
    }
}
