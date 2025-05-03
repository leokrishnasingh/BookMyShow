package com.example.bookmyshow.services;

import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.enums.SeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorImpl implements PriceCalculatorService {
    @Override
    public long calculatePrice(List<ShowSeat> showSeats) {
        long price = 0;
        for (ShowSeat showSeat : showSeats) {
            if(showSeat.getSeat().getSeatType() == SeatType.PLATINUM){
                price += 300L;
            }
            else if (showSeat.getSeat().getSeatType() == SeatType.GOLD){
                price += 200L;
            }
            else{
                price += 100L;
            }
        }
        return price;
    }
}
