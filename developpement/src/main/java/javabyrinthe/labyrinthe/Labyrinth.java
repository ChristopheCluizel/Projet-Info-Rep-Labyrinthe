package javabyrinthe.labyrinthe;

import java.io.Serializable;

import javabyrinthe.tools.Coordinate;
import graph.Graph;

public class Labyrinth implements Serializable {
	Graph graph;
	Integer size;
	Coordinate departure;
	Coordinate arrival;
	
	public Labyrinth(Graph graph, Coordinate departure, Coordinate arrival) {
		this.graph = graph;
		this.departure = departure;
		this.arrival = arrival;
		this.size = graph.getSize();
	}
	
	public Boolean isAuthorizedMove(Coordinate departure, Coordinate arrival) {
		LabyrinthGenerator generateurLab = new LabyrinthGenerator();
		Integer keyDeparture = generateurLab.coordinatesToKey(departure, this.graph.getSize());
		Integer keyArrival = generateurLab.coordinatesToKey(arrival, this.graph.getSize());
		
		return this.graph.getSuccessors(keyDeparture).contains(keyArrival);
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Integer getTaille() {
		return size;
	}

	public void setTaille(Integer size) {
		this.size = size;
	}

	public Coordinate getDepart() {
		return departure;
	}

	public void setDepart(Coordinate departure) {
		this.departure = departure;
	}

	public Coordinate getArrivee() {
		return arrival;
	}

	public void setArrivee(Coordinate arrival) {
		this.arrival = arrival;
	}
}