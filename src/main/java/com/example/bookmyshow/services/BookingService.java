package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowNotAvailable;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking bookTicket(Long showId, List<Long> showSeatId, Long userId) throws UserNotFoundException, ShowNotAvailable;
}
