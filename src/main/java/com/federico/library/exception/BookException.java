package com.federico.library.exception;

import com.federico.library.enumeration.ErrorType;

public class BookException extends GenericException {

	private static final long serialVersionUID = -5073809674725747393L;

	public BookException(String description, ErrorType errorType) {
		super(description, errorType);
	}

}
