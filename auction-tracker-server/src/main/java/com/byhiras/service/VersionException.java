package com.byhiras.service;

public class VersionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public VersionException(String message){
		super(message);
	}
}
