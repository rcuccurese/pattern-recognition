/**
 * This class represent the Rest controller for PatternRecognition application.
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.controller.errors;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PatternRecognitionAppError {

	/**************************************************
						FIELDS
    **************************************************/
    private final String apiVersion;
    private final ErrorBlock error;

	/**************************************************
					CONSTRUCTORS
    **************************************************/
    public PatternRecognitionAppError(final String apiVersion, final String code, final String message, final String domain,
                             final String reason, final String errorMessage) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message, domain, reason, errorMessage);
    }

	/**************************************************
						METHODS
    **************************************************/
    // To make the conversion from the default fields used by Spring Boot
    public static PatternRecognitionAppError fromDefaultAttributeMap(final String apiVersion,
                                                            final Map<String, Object> defaultErrorAttributes) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new PatternRecognitionAppError(
                apiVersion,
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("path", "no domain available"),
                (String) defaultErrorAttributes.getOrDefault("error", "no reason available"),
                (String) defaultErrorAttributes.get("message")
        );
    }

    /*
     * This method is used to add attribute to attributeMap object
     */
    public Map<String, Object> toAttributeMap() {
    	Map<String, Object> attributeMap =new HashMap<String, Object>();
    	attributeMap.put("apiVersion", apiVersion);
    	attributeMap.put("error", error);
    	return attributeMap;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    /*
     * This method is used to get ErrorBlock object
     */
    public ErrorBlock getError() {
        return error;
    }

    /*
     * Private class that represent an ErrorBlock object with all the errors occurred
     */
    private static final class ErrorBlock {

    	/**************************************************
							FIELDS
        **************************************************/
        private final String code;
        private final String message;
        private final List<Error> errors;
        

        /**************************************************
						CONSTRUCTORS
        **************************************************/
        public ErrorBlock(final String code, final String message, final String domain,
                          final String reason, final String errorMessage) {
            this.code = code;
            this.message = message;
            this.errors= new LinkedList<Error>();
            errors.add(new Error(domain, reason, errorMessage));
        }

        
        /**************************************************
							METHODS
        **************************************************/        
        private ErrorBlock(final String code, final String message, final List<Error> errors) {
            this.code = code;
            this.message = message;
            this.errors = errors;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<Error> getErrors() {
            return errors;
        }
    }

    /*
     * Private class that represent an error occurred
     */    
    private static final class Error {

    	/**************************************************
								FIELDS
        **************************************************/
        private final String domain;
        private final String reason;
        private final String message;


    	/**************************************************
							CONSTRUCTORS
        **************************************************/
        public Error(final String domain, final String reason, final String message) {
            this.domain = domain;
            this.reason = reason;
            this.message = message;
        }

        
    	/**************************************************
							METHODS
        **************************************************/        
        public String getDomain() {
            return domain;
        }

        public String getReason() {
            return reason;
        }

        public String getMessage() {
            return message;
        }
    }
}