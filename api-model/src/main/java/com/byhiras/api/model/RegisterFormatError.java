package com.byhiras.api.model;

public enum RegisterFormatError implements ErrorResponseEnum{
    
    RFE01("Mandatory property 'email' not provided"),
    RFE02("Mandatory property 'password' not provided"),
	RFE03("Username property must conform to email pattern"),
	RFE04("Password strength weak"),
	RFE05("Mandatory property 'username' not provided");
    
    private final String systemMessage;

    RegisterFormatError(final String systemMessage) {
        this.systemMessage = systemMessage;
    }

    @Override
    public String getSystemMessage() {
        return systemMessage;
    }
}
