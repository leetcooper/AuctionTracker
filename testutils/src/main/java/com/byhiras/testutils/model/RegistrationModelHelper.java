package com.byhiras.testutils.model;

import org.exparity.stub.random.RandomBuilder;

public class RegistrationModelHelper {	

	public static String aValidRandomUsername() {
		return RandomBuilder.aRandomString();
	}
	
	public static String aValidRandomEmail() {
		return RandomBuilder.aRandomString() + "@otherscape.com";
	}

	public static String aValidRandomPassword(){		
		return RandomBuilder.aRandomString()+"@";
	}	
}
