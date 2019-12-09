/**
 * This class is used to maps exceptions to HTTP responses
 * 
 * 
 * @author Roberto Cuccurese
 *
 */
package com.welld.patternrecognition.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.welld.patternrecognition.rest.controller.errors.PatternRecognitionAppError;
import com.welld.patternrecognition.rest.exception.BadRequestException;
import com.welld.patternrecognition.rest.exception.DuplicatePointException;
import com.welld.patternrecognition.rest.exception.InvalidLineException;

@RestControllerAdvice
@ConditionalOnProperty(name = "pattern.recognition.errors.controlleradvice", havingValue = "true")
public class PatternRecognitionControllerAdvice {
	
	/**************************************************
						FIELDS
    **************************************************/	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${pattern.recognition.api.version}")
	private String currentApiVersion;

	/**************************************************
						METHODS
    **************************************************/	
	/*
	 * This method is used when InvalidLineException occur
	 */
	@ExceptionHandler(InvalidLineException.class)
	public ResponseEntity<PatternRecognitionAppError> handleNonExistingLine(HttpServletRequest request,
			InvalidLineException ex) {
		logger.info("Calling handleNonExistingLine method from PatternRecognitionControllerAdvice class");
		final PatternRecognitionAppError error = new PatternRecognitionAppError(currentApiVersion, ex.getErrorCode(),
				"At least two points are required to draw a line", "pattern-recognition-exceptions",
				"To draw a line are need al least two points", "Drawing line");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * This method is used when DuplicatePointException occur
	 */
	@ExceptionHandler(DuplicatePointException.class)
	public ResponseEntity<PatternRecognitionAppError> handleDuplicatePoint(HttpServletRequest request,
			DuplicatePointException ex) {
		logger.info("Calling handleDuplicatePoint method from PatternRecognitionControllerAdvice class");
		final PatternRecognitionAppError error = new PatternRecognitionAppError(currentApiVersion, ex.getErrorCode(),
				"The point is already present in the points set", "pattern-recognition-exceptions",
				"The point is already present in the points set", "Adding new point");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * This method is used when BadRequestException occur
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<PatternRecognitionAppError> handleBadRequest(HttpServletRequest request,
			BadRequestException ex) {
		logger.info("Calling handleBadRequest method from PatternRecognitionControllerAdvice class");
		final PatternRecognitionAppError error = new PatternRecognitionAppError(currentApiVersion, ex.getErrorCode(),
				"N must be greater than 1", "pattern-recognition-exceptions",
				"N must be greater than 1", "Getting all lines with collinear points");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}