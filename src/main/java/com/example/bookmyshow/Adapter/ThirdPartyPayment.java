package com.example.bookmyshow.Adapter;


import com.example.bookmyshow.Adapter.DTO.ThirdPartyPaymentStatus;
import com.example.bookmyshow.Adapter.DTO.paymentRequestDTO;
import com.example.bookmyshow.Adapter.DTO.paymentResponseDTO;
import com.example.bookmyshow.models.Payment;

import java.util.ArrayList;
import java.util.List;

public class ThirdPartyPayment implements PaymentAdapter {
    @Override
    public paymentResponseDTO initiatePayment(paymentRequestDTO requestDTO) {
        paymentResponseDTO responseDTO = new paymentResponseDTO();

        //payment scenario
        List<Payment> paymentList = new ArrayList<Payment>();
        paymentList.add(new Payment());
        responseDTO.setPaymentStatus(ThirdPartyPaymentStatus.SUCCESS);
        responseDTO.setReferanceNumber("TestNumber123456");
        responseDTO.setPaymentList(paymentList);
        return responseDTO;
    }
}
