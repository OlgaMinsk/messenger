package com.innowisegroup.messenger.dto.response;

public class AuthenticationResponse {
    private final String username;
    private final String token;

    public AuthenticationResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

}
