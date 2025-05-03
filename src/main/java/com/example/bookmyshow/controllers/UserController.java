package com.example.bookmyshow.controllers;


import com.example.bookmyshow.dto.ResponseStatus;
import com.example.bookmyshow.dto.SignupRequestDTO;
import com.example.bookmyshow.dto.SignupResponseDTO;
import com.example.bookmyshow.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO requestDTO){
        SignupResponseDTO responseDTO = new SignupResponseDTO();
        try{
            responseDTO.setUser(userService.saveUser(requestDTO.getName(),
                    requestDTO.getEmail(), requestDTO.getPassword()));
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

}
