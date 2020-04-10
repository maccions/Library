package com.federico.library.exception;

import com.federico.library.enumeration.ErrorType;

public class BookShelfException extends GenericException {

	private static final long serialVersionUID = -5073809674725747393L;

	public BookShelfException(String description, ErrorType errorType) {
		super(description, errorType);
	}

}
