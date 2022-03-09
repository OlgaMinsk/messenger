package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.AuthenticationRequest;
import com.innowisegroup.messenger.dto.response.AuthenticationResponse;
import com.innowisegroup.messenger.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messengerAPI/v01/auth/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequest requestDto) {
        AuthenticationResponse response = authenticationService.login(requestDto);
        return ResponseEntity.ok(response);

    }

}
