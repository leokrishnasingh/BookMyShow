package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;
import com.example.bookmyshow.models.User;


@Getter
@Setter
public class SignupResponseDTO {
    private User user;
    private ResponseStatus status;
}
