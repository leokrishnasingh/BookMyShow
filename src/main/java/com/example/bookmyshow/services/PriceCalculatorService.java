package com.example.bookmyshow.services;

import com.example.bookmyshow.models.ShowSeat;

import java.util.List;

public interface PriceCalculatorService {

    long calculatePrice(List<ShowSeat> showSeats);
}
