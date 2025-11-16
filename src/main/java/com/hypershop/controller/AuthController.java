package com.hypershop.controller;

import com.hypershop.dto.request.AddUserRequest;
import com.hypershop.dto.request.OtpRequest;
import com.hypershop.dto.request.OtpVerifyRequest;
import com.hypershop.dto.response.GlobalResponse;
import com.hypershop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;


    @PostMapping("/consumer/login/requestOtp")
    public GlobalResponse<?> requestLoginOtp(@Valid @RequestBody OtpRequest request) {
       return authService.sendConsumerLoginOtp(request);
    }

    @PostMapping("/consumer/login/verifyOtp")
    public GlobalResponse<?> verifyOtpAndLogin(@Valid @RequestBody OtpVerifyRequest request) {
       return authService.verifyConsumerLoginOtp(request);
    }

    @PostMapping("/admin/login/requestOtp")
    public GlobalResponse<?> requestAdminLoginOtp(@Valid @RequestBody OtpRequest request) {
       return authService.sendAdminLoginOtp(request);
    }

    @PostMapping("/admin/login/verifyOtp")
    public GlobalResponse<?> verifyAdminOtpAndLogin(@Valid @RequestBody OtpVerifyRequest request) {
       return authService.verifyConsumerLoginOtp(request);
    }

    @PostMapping("/rider/login/requestOtp")
    public GlobalResponse<?> requestRiderLoginOtp(@Valid @RequestBody OtpRequest request) {
       return authService.sendRiderLoginOtp(request);
    }

    @PostMapping("/rider/login/verifyOtp")
    public GlobalResponse<?> verifyRiderOtpAndLogin(@Valid @RequestBody OtpVerifyRequest request) {
       return authService.verifyConsumerLoginOtp(request);
    }

    @PostMapping("/manager/login/requestOtp")
    public GlobalResponse<?> requestManagerLoginOtp(@Valid @RequestBody OtpRequest request) {
       return authService.sendManagerLoginOtp(request);
    }

    @PostMapping("/manager/login/verifyOtp")
    public GlobalResponse<?> verifyManagerOtpAndLogin(@Valid @RequestBody OtpVerifyRequest request) {
       return authService.verifyConsumerLoginOtp(request);
    }


    @PostMapping("addUser")
    public GlobalResponse<?> addUser(@Valid @RequestBody AddUserRequest request) {
        return authService.addUser(request);
    }
}