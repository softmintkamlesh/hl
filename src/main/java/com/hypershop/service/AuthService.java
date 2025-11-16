package com.hypershop.service;


import com.hypershop.dto.request.AddUserRequest;
import com.hypershop.dto.request.OtpRequest;
import com.hypershop.dto.request.OtpVerifyRequest;
import com.hypershop.dto.response.GlobalResponse;
import jakarta.validation.Valid;

public interface AuthService {

    GlobalResponse<?> sendConsumerLoginOtp(OtpRequest request);


    GlobalResponse<?> verifyConsumerLoginOtp(OtpVerifyRequest request);

    GlobalResponse<?> sendRiderLoginOtp(OtpRequest request);

    GlobalResponse<?> sendManagerLoginOtp(OtpRequest request);

    GlobalResponse<?> sendAdminLoginOtp(OtpRequest request);

    GlobalResponse<?> addUser(@Valid AddUserRequest request);
}