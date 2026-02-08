package com.loopstudio.backcountryvoting.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
	public EmailAlreadyRegisteredException(String email) {
        super("The email '" + email + "' has already been registered");
    }
}
