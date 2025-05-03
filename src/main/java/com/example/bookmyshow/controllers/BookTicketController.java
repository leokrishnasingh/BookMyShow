package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.BookingResponseDTO;
import com.example.bookmyshow.dto.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import jakarta.annotation.Resource;

import java.util.List;

@Resource
public class BookTicketController {

    private final BookingService bookingService;
    BookTicketController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDTO bookTicket(Long showId,
                                         List<Long> showSeatIds, Long userId) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        try{
            Booking booking = bookingService.bookTicket(showId, showSeatIds, userId);
            bookingResponseDTO.setBooking(booking);
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        return bookingResponseDTO;
    }
}
