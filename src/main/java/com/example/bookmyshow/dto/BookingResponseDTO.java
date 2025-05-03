package com.example.bookmyshow.dto;

import com.example.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {
    private Booking booking;
    private ResponseStatus responseStatus;
}
