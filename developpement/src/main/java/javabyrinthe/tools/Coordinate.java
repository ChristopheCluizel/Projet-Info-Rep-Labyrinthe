package javabyrinthe.tools;

import java.io.Serializable;

public class Coordinate implements Serializable {
	Integer x;
	Integer y;
	
	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return this.x;
	}
	
	public Integer getY() {
		return this.y;
	}
	
	@Override 
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
