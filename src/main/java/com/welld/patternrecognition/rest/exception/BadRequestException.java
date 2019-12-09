/**
 * This custom exception is thrown when try to call API that 
 * allow to get all line segments passing through at least N points where N < 2
 *
 *
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.exception;

import com.welld.patternrecognition.rest.controller.errors.ErrorCode;

public class BadRequestException extends RuntimeException implements ErrorCode {

	/**************************************************
						FIELDS
    **************************************************/
	private static final long serialVersionUID = 1L;


	/**************************************************
					CONSTRUCTORS
    **************************************************/
	public BadRequestException(final String message) {
        super(message);
    }


	/**************************************************
						METHODS
    **************************************************/
    @Override
    public String getErrorCode() {
        return "NE-003";
    }
}