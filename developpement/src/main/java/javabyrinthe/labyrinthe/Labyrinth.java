package javabyrinthe.labyrinthe;

import java.io.Serializable;

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
		this.size = graph.getSize();
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
		Integer keyDeparture = labGenerator.coordinatesToKey(departure, this.graph.getSize());
		Integer keyArrival = labGenerator.coordinatesToKey(arrival, this.graph.getSize());
		
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
}