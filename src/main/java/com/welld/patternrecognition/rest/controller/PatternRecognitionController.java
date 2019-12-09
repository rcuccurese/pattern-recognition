/**
 * This class represent the Rest controller for PatternRecognition application.
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.welld.patternrecognition.rest.dto.PointDTO;
import com.welld.patternrecognition.rest.service.PatternRecognitionService;

@RestController
public class PatternRecognitionController {

	/**************************************************
	 					FIELDS
	**************************************************/
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final PatternRecognitionService service;

	
	/**********************************************
					CONSTRUCTORS
	**************************************************/
	public PatternRecognitionController(PatternRecognitionService service) {
		this.service = service;
	}
	

	/**************************************************
						METHODS
	**************************************************/
	/*
	 * This API allow to add a point to the space.
	 */
	@PostMapping("/point")
	public ResponseEntity<PointDTO> addPointToTheSpace(@RequestBody PointDTO pointDTO) {
		logger.info("Calling addPointToTheSpace API {} from PatternRecognitionController class", pointDTO);
		service.addPointToTheSpace(pointDTO);
		return new ResponseEntity<PointDTO>(pointDTO, HttpStatus.OK);
	}

	/*
	 * This API allow to get all points in the space.
	 */
	@GetMapping("/space")
	public ResponseEntity getAllPointsInTheSpace() {
		logger.info("Calling getAllPointsInTheSpace API from PatternRecognitionController class");
		return retrieveResponseByCollection(service.getAllPointsInTheSpace());
	}

	/*
	 * This API allow to get all line segments passing through at least N points.
	 * Note that a line segment should be a set of points.
	 */
	@GetMapping("/lines/{n}")
	public ResponseEntity getAllLinesSegment(@PathVariable int n) {
		logger.info("Calling getAllLinesSegment API {} from PatternRecognitionController class", n);
		return retrieveResponseByCollection(service.getAllLines(n));
	}

	/*
	 * This API allow to remove all points from the space.
	 */
	@DeleteMapping("/space")
	public ResponseEntity<String> removeAllPointsFromTheSpace() {
		logger.info("Calling removeAllPointsFromTheSpace API from PatternRecognitionController class");
		service.removeAllPointsFromTheSpace();
		return new ResponseEntity<String>("removeAllPointsFromTheSpace API terminated with success", HttpStatus.OK);
	}

	private ResponseEntity<Collection> retrieveResponseByCollection(Collection collection) {
		logger.info("Calling retrieveResponseByCollection method from PatternRecognitionController class");
		if (collection.isEmpty()) {
			logger.info("Collection returned is empty");
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<Collection>(collection, HttpStatus.OK);
	}
}