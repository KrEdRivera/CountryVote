package com.loopstudio.backcountryvoting.exception;

public class CountryNotFoundException extends RuntimeException {
	public CountryNotFoundException(String country) {
        super("El país '" + country + "' no fue encontrado");
    }
}
