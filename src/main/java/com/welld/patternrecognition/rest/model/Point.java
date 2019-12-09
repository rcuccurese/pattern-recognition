/**
 * This class represent a Point with x,y coordinate
 * 
 * 
 * @author Roberto Cuccurese
 * 
 */
package com.welld.patternrecognition.rest.model;

public class Point {
	/**************************************************
						FIELDS
	**************************************************/
	private double x;
	private double y;
	

	/**************************************************
					 CONSTRUCTORS
    **************************************************/
	private Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	

	/**************************************************
						METHODS
    **************************************************/	
	public static Point getPointFromCoordinates(double x, double y) {
		return new Point(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Point(" + "x=" + x + ", y=" + y + ')';
	}

	@Override
	public boolean equals(Object objectToCompare) {
		if (this == objectToCompare) {
			return true;
		}

		if (!(objectToCompare instanceof Point)) {
			return false;
		}

		Point pointToCompare = (Point) objectToCompare;
		return (this.getX() == pointToCompare.getX() && this.getY() == pointToCompare.getY());
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((Double) this.getX()).hashCode();
		result = prime * result + ((Double) this.getY()).hashCode();
		return result;
	}
}