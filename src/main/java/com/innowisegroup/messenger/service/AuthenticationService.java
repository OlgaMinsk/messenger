package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.AuthenticationRequest;
import com.innowisegroup.messenger.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest requestDto);
}
