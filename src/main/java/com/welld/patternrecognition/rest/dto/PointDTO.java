/**
 * This class is used for the automatic conversion from JSON response to PointDTO object
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.dto;

public class PointDTO {
	
	/**************************************************
						FIELDS
    **************************************************/
	private double x;
	private double y;

	
	/**************************************************
						CONSTRUCTORS
    **************************************************/
	public PointDTO() {
		super();
	}

	public PointDTO(double x, double y) {
		this.x = x;
		this.y = y;
	}

	
	/**************************************************
						METHODS
    **************************************************/
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}