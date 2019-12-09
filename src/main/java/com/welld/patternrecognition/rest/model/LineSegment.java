/**
 * This class represent a line segment that is a Set of Point object
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.welld.patternrecognition.rest.exception.InvalidLineException;

public class LineSegment {

	/**************************************************
						FIELDS
	**************************************************/
	private static Logger logger = LoggerFactory.getLogger(LineSegment.class);
    private Set<Point> points;

	/**************************************************
					 CONSTRUCTORS
	**************************************************/
    private LineSegment(Set<Point> points) {
        this.points = points;
    }

	/**************************************************
					   METHODS
	**************************************************/
    /*
     * Given 0 or more Point objects, return a LineSegment object 
     * if there are almost 2 Point objects otherwise throw InvalidLineException
     */
    public static LineSegment getLineSegmentFromPoints(Point... points) throws InvalidLineException {
    	logger.info("Calling getLineSegmentFromPoints method from LineSegment class");
        final Set<Point> pointSet = Stream.of(points)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if(pointSet.size() < 2) {
            throw new InvalidLineException("At least two points are required to draw a line");
        }
        return new LineSegment(pointSet);
    }

    /*
     * This method is used to get all points that composing a LineSegment object (Set<Point>)
     */
    public List<Point> getAllPoints() {
        return new LinkedList<Point>(points);
    }

    /*
     * This method is used to add a try to add a new Point object to LineSegment object (Set<Point>)
     */
    public void addPoint(Point newPoint) {
    	logger.info("Calling addPoint method from LineSegment class");
        points.add(newPoint);
    }

    @Override
    /*
     * This method is used to represent as a String object a LineSegment object
     */
    public String toString() {
        return "LineSegment{" + "points=" + points + '}';
    }
}