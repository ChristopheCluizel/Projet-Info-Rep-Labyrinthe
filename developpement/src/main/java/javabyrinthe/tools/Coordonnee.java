package javabyrinthe.tools;

public class Coordonnee {
	Integer x;
	Integer y;
	
	public Coordonnee(Integer x, Integer y) {
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
