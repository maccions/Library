package com.federico.library.exception;

import com.federico.library.enumeration.ErrorType;

public class AuthorException extends GenericException {

	private static final long serialVersionUID = 3086929404371932277L;

	public AuthorException(String description, ErrorType errorType) {
		super(description, errorType);
	}

}
