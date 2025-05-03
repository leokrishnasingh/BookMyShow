package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.BookingStatus;
import com.example.bookmyshow.models.ShowSeat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private String bookingNumber;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToMany
    private List<ShowSeat> seats; //cancellation is also an feature we are supporting

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    @ManyToOne
    private User user;

    private Long amount;
}
