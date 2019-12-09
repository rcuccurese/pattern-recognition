/**
 * This custom exception is thrown when there are < 2 points to draw a line
 *
 *
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.exception;

import com.welld.patternrecognition.rest.controller.errors.ErrorCode;

public class InvalidLineException extends RuntimeException implements ErrorCode {

	/**************************************************
						FIELDS
    **************************************************/
	private static final long serialVersionUID = 1L;


	/**************************************************
					CONSTRUCTORS
    **************************************************/
	public InvalidLineException(final String message) {
        super(message);
    }


	/**************************************************
						METHODS
    **************************************************/
    @Override
    public String getErrorCode() {
        return "NE-001";
    }
}