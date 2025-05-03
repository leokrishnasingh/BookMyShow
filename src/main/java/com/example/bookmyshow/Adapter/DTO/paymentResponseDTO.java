package com.example.bookmyshow.Adapter.DTO;

import com.example.bookmyshow.Adapter.DTO.ThirdPartyPaymentStatus;
import com.example.bookmyshow.models.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class paymentResponseDTO {
    private String referanceNumber;
    private ThirdPartyPaymentStatus paymentStatus;
    private List<Payment> paymentList;
}
