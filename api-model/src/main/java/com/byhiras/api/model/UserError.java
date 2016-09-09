package com.byhiras.api.model;

public enum UserError implements ErrorResponseEnum{
    
    UE01("User exists already"),
	UE02("User does not exists");
    
    private final String systemMessage;

    UserError(final String systemMessage) {
        this.systemMessage = systemMessage;
    }

    @Override
    public String getSystemMessage() {
        return systemMessage;
    }
}
