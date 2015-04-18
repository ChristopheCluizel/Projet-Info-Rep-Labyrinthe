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
	Integer size;
	Coordinate departure;
	Coordinate arrival;
	
	/**
	 * Create a labyrinth with a graph conception, a departure square and an arrival square
	 * 
	 * @param graph the graph of the labyrinth
	 * @param departure the coordinate of the departure square
	 * @param arrival the coordinate of the arrival square
	 * @see Graph
	 * @see Coordinate
	 */
	public Labyrinth(Graph graph, Coordinate departure, Coordinate arrival) {
		this.graph = graph;
		this.departure = departure;
		this.arrival = arrival;
		this.size = (int)Math.sqrt(graph.getSize());
	}
	
	/**
	 * Test if 2 squares are adjacent without a wall between them, so if it's an authorized move
	 * 
	 * @param departure the coordinate of the departure square
	 * @param arrival the coordinate of the arrival square
	 * @return whether it's possible to go from a square to the other one
	 * @see Coordinate
	 * @see LabyrinthGenerator
	 */
	public Boolean isAuthorizedMove(Coordinate departure, Coordinate arrival) {
		LabyrinthGenerator labGenerator = new LabyrinthGenerator();
		Integer keyDeparture = labGenerator.coordinatesToKey(departure, this.size);
		Integer keyArrival = labGenerator.coordinatesToKey(arrival, this.size);
		System.out.println("Déplacement: " + keyDeparture + " -> " + keyArrival);
		
		return this.graph.getSuccessors(keyDeparture).contains(keyArrival);
	}
	
	/**
	 * Get the graph of the labyrinth
	 * 
	 * @return the graph of the labyrinth
	 * @see Graph
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * Get the size of the labyrinth
	 * 
	 * @return the size of the labyrinth
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Get the departure square coordinate of the labyrinth
	 * 
	 * @return the coordinate of the departure square of the labyrinth
	 * @see Coordinate
	 */
	public Coordinate getDeparture() {
		return departure;
	}

	/**
	 * Get the arrival square coordinate of the labyrinth
	 * 
	 * @return the coordinate of the arrival square of the labyrinth
	 * @see Coordinate
	 */
	public Coordinate getArrival() {
		return arrival;
	}
	
	public Coordinate getNextSquareFromOrder(String order, Coordinate actualPosition) {
		
		switch(order) {
			case "HAUT":
				return new Coordinate(actualPosition.getX(), Math.max(actualPosition.getY() - 1, 0));

			case "BAS":
				return new Coordinate(actualPosition.getX(), Math.min(actualPosition.getY() + 1, this.getSize()-1));
			case "GAUCHE":
				return new Coordinate(Math.max(0, actualPosition.getX() - 1), actualPosition.getY());
				
			case "DROITE":
				return new Coordinate(Math.min(this.getSize()-1, actualPosition.getX() + 1), actualPosition.getY());
		}
		return null;
	}
}