package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {
    private Long showId;
    private Long userId;
    private List<Long> showSeatIds;
}
