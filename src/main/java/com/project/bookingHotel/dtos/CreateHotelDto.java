package com.project.bookingHotel.dtos;

public record CreateHotelDto(String name, String descriptionHotel, String city, Integer numberOfRooms, Integer numberOfRoomsAvaiable) {
}
