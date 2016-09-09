package com.byhiras.api.model;

public class RegisterResponse extends BaseErrorResponse{
	private String email;
	
	public RegisterResponse() {
		
	}	

	public RegisterResponse(final ErrorResponse errorResponse) {
		super(errorResponse);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
