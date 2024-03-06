package com.store.nemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UniquenessConstraintViolation extends RuntimeException {

	public UniquenessConstraintViolation(String exMessage) {
		super(exMessage);
	}
}
