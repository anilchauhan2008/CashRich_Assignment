package com.anilchauhan.cashrich_assignment.beans;

public class LoginResponse {
	   private String token;

	    public LoginResponse(String token) {
	        this.token = token;
	    }

	    // Getter
	    public String getToken() {
	        return token;
	    }

}
