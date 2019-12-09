/**
 * This class represent a Space object that is a set of Point objects
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.welld.patternrecognition.rest.exception.BadRequestException;
import com.welld.patternrecognition.rest.exception.DuplicatePointException;

public class Space {
	/**************************************************
						FIELDS
    **************************************************/
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// points field represent the space
	private Set<Point> points;
	//linesInSpace field is a Map where:
	// key: Integer value that represent the number of the Point objects of a 
	private Map<Integer, List<LineSegment>> linesInSpace;
	
	
	/**************************************************
					  CONSTRUCTORS
    **************************************************/
	public Space() {
		spaceInitialization();
	}

	
	/**************************************************
						METHODS
    **************************************************/
	/*
	 * This method is used to add a new point to the space
	 */
	public void addPoint(final Point newPoint) throws DuplicatePointException {
		logger.info("Calling addPoint method from Space class");

		// Check if newPoint is already present in the space (points)
		if (!points.contains(newPoint)) {
			// The new point not is already present in the space,
			// check if be part of some line
			recalculateLineSegments(newPoint);
			this.points.add(newPoint);
		}
		else {
			logger.info("The point is already present in the points set");
			throw new DuplicatePointException("The point is already present in the points set");
		}
	}

	/*
	 * This method is used to get all the points present into the space
	 */	
	public Set<Point> getPoints() {
		logger.info("Calling getPoints method from Space class");
		return points;
	}

	/*
	 * This method is used to remove all the points present into the space
	 */	
	public void removeAllPoints() {
		logger.info("Calling removeAllPoints method from Space class");
		spaceInitialization();
	}

	/*
	 * This method is used to get all the lines present into the space with collinear point
	 */	
	public List<LineSegment> getLinesWithCollinearPoints(int n) {
		logger.info("Calling getLinesWithCollinearPoints method from Space class");
		if (n < 2) {
			throw new BadRequestException("N must be greater than 1");
		}
		return this.linesInSpace.entrySet().stream().filter(v -> v.getKey() >= n).map(Map.Entry::getValue)
				.flatMap(Collection::stream).collect(Collectors.toList());
	}

	/*
	 * This method is invoked to establish if a generic point 
	 */
	public boolean isPointCollinearToLineSegment(final Point newPoint, final LineSegment lineSegment) {
		logger.info("Calling isPointCollinearToLine method from Space class");
		
		// linePoints field contains all the points that represent the line
		final List<Point> linePoints = lineSegment.getAllPoints();
		 
		// Getting first two points that represent the line
		final Point point1 = linePoints.get(0);
		final Point point2 = linePoints.get(1);

		double result = ( newPoint.getX() * (point2.getY() - point1.getY()) ) + 
				        ( newPoint.getY() * (point1.getX() - point2.getX()) ) +  
				        ( point2.getX() * point1.getY() - point1.getX() * point2.getY());
		return result == 0d;
	}

	/*
	 * This method is used for the initialization of the space
	 */	
	private void spaceInitialization() {
		logger.info("Calling spaceInitialization method from Space class");
		this.points = new HashSet<Point>();
		this.linesInSpace = new HashMap<Integer, List<LineSegment>>();
	}

	/*
	 * This method is called when we try to add a new point to the space, than it checks 
	 * if there are any existing LineSegment can be extended, eventually redraw it with the new point.
	 */	
	private void recalculateLineSegments(final Point newPoint) {
		logger.info("Calling recalculateLineSegments method from Space class");
		// lines is a map that contains the number of the point that represent the line
		// and the line itself
		Map<Integer, List<LineSegment>> lines = new HashMap<Integer, List<LineSegment>>();

		this.linesInSpace.values().stream().flatMap(Collection::stream).forEach(line -> {
			if (isPointCollinearToLineSegment(newPoint, line)) {
				// Adding new point to the set (line)
				line.addPoint(newPoint);
			}
			
			List<LineSegment> lineList = lines.get(line.getAllPoints().size());
			if (lineList == null) {
				lineList = new LinkedList<LineSegment>();
			}
			lineList.add(line);
			logger.info("recalculateLineSegments recalculated {}", line);
			lines.put(line.getAllPoints().size(), lineList);
		});

		this.linesInSpace = lines;
		drawNewLineSegment(newPoint);
	}

	/*
	 * This method is used to draw all LineSegment objects composed by two points	
	 */		
	private void drawNewLineSegment(final Point point) {
		logger.info("Calling drawNewLineSegment method from Space class");
		final List<LineSegment> twoPointsLine;
		
		if (this.linesInSpace.get(2) != null) {
			twoPointsLine = this.linesInSpace.get(2);
		} else {
			twoPointsLine = new LinkedList<LineSegment>();
		}

		this.points.forEach(p -> {
			if (!alreadyInLine(point, p)) {
				logger.info("Drawing new line between point {} and {}", point, p);
				twoPointsLine.add(LineSegment.getLineSegmentFromPoints(point, p));
			}
		});

		if (!twoPointsLine.isEmpty()) {
			logger.info("Adding the new line composed by 2 point to the linesMap object");
			this.linesInSpace.put(2, twoPointsLine);
		}
	}

	/*
	 * This method is used to check if given 2 points it belongs to the same line
	 */		
	private boolean alreadyInLine(Point point1, Point point2) {
		logger.info("Calling alreadyInLine method from Space class");
		
		// Getting two points that belong the line
		List<Point> points = new LinkedList<Point>();
		points.add(point1);
		points.add(point2);

		return this.linesInSpace.values().stream().flatMap(Collection::stream)
				.anyMatch(line -> line.getAllPoints().containsAll(points));
	}
}