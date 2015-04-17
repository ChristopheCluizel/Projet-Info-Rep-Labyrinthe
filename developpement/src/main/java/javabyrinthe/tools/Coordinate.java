package javabyrinthe.tools;

import java.io.Serializable;

/**
 * This class represents a coordinate in 2 dimensions
 * @author christophe
 */
public class Coordinate implements Serializable {
	Integer x;
	Integer y;
	
	/**
	 * Construct a Coordinate with an initialization
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate
	 * 
	 * @return the x coordinate
	 */
	public Integer getX() {
		return this.x;
	}
	
	/**
	 * Get the y coordinate
	 * 
	 * @return the y coordinate
	 */
	public Integer getY() {
		return this.y;
	}
	
	@Override 
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
