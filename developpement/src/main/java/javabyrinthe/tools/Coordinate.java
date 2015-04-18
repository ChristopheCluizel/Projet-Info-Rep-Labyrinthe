package javabyrinthe.tools;

import java.io.Serializable;

/**
 * This class represents a coordinate in 2 dimensions
 * @author christophe
 */
public class Coordinate implements Serializable {
	int x;
	int y;
	
	/**
	 * Construct a Coordinate with an initialization
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate
	 * 
	 * @return the x coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get the y coordinate
	 * 
	 * @return the y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Compare 2 Coordinates
	 * 
	 * @param that the coordinate to compare with
	 * @return if the 2 coordinates are equal
	 */
	public Boolean equals(Coordinate that) {
		return (this.x == that.getX() && this.y == that.getY());
	}
	
	@Override 
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
