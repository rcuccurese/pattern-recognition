/**
 * This class represent the attributes of the error occurred
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.controller.errors;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

class PatternRecognitionAppErrorAttributes extends DefaultErrorAttributes {
	
	/**************************************************
						FIELDS
    **************************************************/
    private final String currentApiVersion;

    

	/**************************************************
						COSTRUCTORS
    **************************************************/
    public PatternRecognitionAppErrorAttributes(final String currentApiVersion) {
        this.currentApiVersion = currentApiVersion;
    }

    
	/**************************************************
						METHODS
    **************************************************/
    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, final boolean includeStackTrace) {
        final Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, false);
        final PatternRecognitionAppError patternRecognitionAppError = PatternRecognitionAppError.fromDefaultAttributeMap(
                currentApiVersion, defaultErrorAttributes);
        return patternRecognitionAppError.toAttributeMap();
    }
}