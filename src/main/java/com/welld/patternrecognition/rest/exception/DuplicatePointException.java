/**
 * This custom exception is thrown when try to add a new point already present in the space
 *
 *
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.exception;

import com.welld.patternrecognition.rest.controller.errors.ErrorCode;

public class DuplicatePointException extends RuntimeException implements ErrorCode {

	/**************************************************
						FIELDS
    **************************************************/
	private static final long serialVersionUID = 1L;


	/**************************************************
					CONSTRUCTORS
    **************************************************/
	public DuplicatePointException(final String message) {
        super(message);
    }


	/**************************************************
						METHODS
    **************************************************/
    @Override
    public String getErrorCode() {
        return "NE-002";
    }
}