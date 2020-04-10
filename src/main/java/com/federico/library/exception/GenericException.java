package com.federico.library.exception;

import com.federico.library.enumeration.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class GenericException extends Exception {

	private static final long serialVersionUID = 8097932493899619117L;
	
	private String description;
	private ErrorType error;

}
