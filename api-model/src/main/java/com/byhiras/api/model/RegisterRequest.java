package com.byhiras.api.model;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RegisterRequest extends BaseRequest{
	@NotBlank(message="RFE05")
	private String username = "";
	@NotBlank(message="RFE01")
    @Pattern(regexp=".+@.+\\.[a-z]+", message="RFE03")
    private String email = "";
    @NotBlank(message="RFE02")
    @Pattern(regexp="(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="RFE04")
    private String password = "";
	private String referralUsername;    

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReferralUsername() {
		return referralUsername;
	}

	public void setReferralUsername(String referralUsername) {
		this.referralUsername = referralUsername;
	}
		
}
