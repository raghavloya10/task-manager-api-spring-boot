package io.rl.tm_api.security;

public class AuthResponse {
    
    String token;

    public String getToken() {
        return this.token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }    

}
