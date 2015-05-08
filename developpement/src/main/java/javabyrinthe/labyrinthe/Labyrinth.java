package javabyrinthe.labyrinthe;

import java.io.Serializable;
import java.lang.Math;

import javabyrinthe.tools.Coordinate;
import graph.Graph;

/**
 * This class represents a labyrinth
 * 
 * @author christophe
 */
public class Labyrinth implements Serializable {
	Graph graph;
	String name;
	int width;
	int height;
	Coordinate departure;
	Coordinate arrival;

	/**
	 * Create a labyrinth with a graph conception, a departure square and an
	 * arrival square
	 * 
	 * @param graph the graph of the labyrinth
	 * @param departure the coordinate of the departure square
	 * @param arrival the coordinate of the arrival square
	 * @see Graph
	 * @see Coordinate
	 */
	public Labyrinth(Graph graph, Coordinate departure, Coordinate arrival, int width, int height, String name) {
		this.graph = graph;
		this.name = name;
		this.departure = departure;
		this.arrival = arrival;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return this.name + " " + this.width + " " + this.height + "\n" + this.departure.getX() + " "
				+ this.departure.getY() + "\n" + this.arrival.getX() + " " + this.arrival.getY() + "\n"
				+ graph.toString();
	}
	
	public String toStringForUser() {
		return this.width + " " + this.height + "\n" + this.departure.getX() + " "
				+ this.departure.getY() + "\n" + this.arrival.getX() + " " + this.arrival.getY() + "\n"
				+ graph.toStringForUser();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	/**
	 * Test if 2 squares are adjacent without a wall between them, so if it's an
	 * authorized move
	 * 
	 * @param departure the coordinate of the departure square
	 * @param arrival the coordinate of the arrival square
	 * @return whether it's possible to go from a square to the other one
	 * @see Coordinate
	 * @see LabyrinthGenerator
	 */
	public Boolean isAuthorizedMove(Coordinate departure, Coordinate arrival) {
		LabyrinthGenerator labGenerator = new LabyrinthGenerator();
		Integer keyDeparture = labGenerator.coordinatesToKey(departure, this.width);
		Integer keyArrival = labGenerator.coordinatesToKey(arrival, this.width);
		//System.out.println("Déplacement: " + keyDeparture + " -> " + keyArrival);

		return this.graph.getSuccessors(keyDeparture).contains(keyArrival);
	}

	/**
	 * Get the graph of the labyrinth
	 * 
	 * @return the graph of the labyrinth
	 * @see Graph
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Get the number of squares of the labyrinth
	 * 
	 * @return the number of squares of the labyrinth
	 */
	public Integer getSize() {
		return this.graph.getNodes().size();
	}

	/**
	 * Get the departure square coordinate of the labyrinth
	 * 
	 * @return the coordinate of the departure square of the labyrinth
	 * @see Coordinate
	 */
	public Coordinate getDeparture() {
		return this.departure;
	}

	/**
	 * Get the arrival square coordinate of the labyrinth
	 * 
	 * @return the coordinate of the arrival square of the labyrinth
	 * @see Coordinate
	 */
	public Coordinate getArrival() {
		return this.arrival;
	}

	/**
	 * Set the arrival square coordinate of the labyrinth
	 * 
	 * @param arrival the arrival square coordinate of the labyrinth
	 * @see Coordinate
	 */
	public void setArrival(Coordinate arrival) {
		this.arrival = arrival;
	}

	/**
	 * Return the coordinate of the next square according to a move order
	 * 
	 * @param order the order of move (HAUT, BAS, GAUCHE, DROITE)
	 * @param actualPosition the coordinate of the actual position
	 * @return the coordinate of the next square
	 * @see Coordinate
	 */
	public Coordinate getNextSquareFromOrder(String order, Coordinate actualPosition) {

		switch (order) {
		case "HAUT":
			return new Coordinate(actualPosition.getX(), Math.max(actualPosition.getY() - 1, 0));

		case "BAS":
			return new Coordinate(actualPosition.getX(), Math.min(actualPosition.getY() + 1, this.getSize() - 1));
		case "GAUCHE":
			return new Coordinate(Math.max(0, actualPosition.getX() - 1), actualPosition.getY());

		case "DROITE":
			return new Coordinate(Math.min(this.getSize() - 1, actualPosition.getX() + 1), actualPosition.getY());
		}
		return null;
	}
}