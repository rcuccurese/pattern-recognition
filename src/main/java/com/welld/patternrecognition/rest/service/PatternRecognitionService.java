/**
 * This class provide some business functionalities. 
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.welld.patternrecognition.rest.dto.PointDTO;
import com.welld.patternrecognition.rest.model.Point;
import com.welld.patternrecognition.rest.model.Space;

@Service
public class PatternRecognitionService {

	/**************************************************
						FIELDS
	**************************************************/
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Space space = new Space();
	private Function<Point, PointDTO> pointDTOMapper = pointDTO -> new PointDTO(pointDTO.getX(), pointDTO.getY());

	
	/**************************************************
					   METHODS
	**************************************************/
	/*
	 * This method allow to add a new point to the space.
	 */
	public void addPointToTheSpace(final PointDTO pointDTO) {
		logger.info("Calling addPointToTheSpace {} method from PatternRecognitionService class", pointDTO);
		Assert.notNull(pointDTO, "The new point cannot be null");
		space.addPoint(Point.getPointFromCoordinates(pointDTO.getX(), pointDTO.getY()));
	}

	/*
	 * This method allow to get all points present in the space.
	 */
	public Collection<PointDTO> getAllPointsInTheSpace() {
		logger.info("Calling getAllPointsInTheSpace method from PatternRecognitionService class");
		return this.space.getPoints().stream().map(pointDTOMapper).collect(Collectors.toSet());
	}

	/*
	 * This method allow to get all lines segments passing through at least N points.
	 * Note that a line segment should be a set of points.
	 */
	public List<Set<PointDTO>> getAllLines(int n) {
		logger.info("Calling getAllLines {} method from PatternRecognitionService class", n);
		return this.space.getLinesWithCollinearPoints(n).stream()
				.map(l -> l.getAllPoints().stream().map(pointDTOMapper).collect(Collectors.toSet()))
				.collect(Collectors.toList());
	}
	
	/*
	 * This method allow to remove all points present in the space.
	 */
	public void removeAllPointsFromTheSpace() {
		logger.info("Calling removeAllPointsFromTheSpace {} method from PatternRecognitionService class", space);
		space.removeAllPoints();
	}
}