package com.federico.library.dto;

import com.federico.library.enumeration.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
	
	String message;
	ErrorType errorType;
}
