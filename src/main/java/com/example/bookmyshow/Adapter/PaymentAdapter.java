package com.example.bookmyshow.Adapter;

import com.example.bookmyshow.Adapter.DTO.paymentRequestDTO;
import com.example.bookmyshow.Adapter.DTO.paymentResponseDTO;

public interface PaymentAdapter {

    paymentResponseDTO initiatePayment (paymentRequestDTO requestDTO);
}
