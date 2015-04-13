package javabyrinthe.labyrinthe;

import java.io.Serializable;

import javabyrinthe.tools.Coordonnee;
import graph.Graph;

public class Labyrinthe implements Serializable {
	Graph graph;
	Integer taille;
	Coordonnee depart;
	Coordonnee arrivee;
	
	public Labyrinthe(Graph graph, Coordonnee depart, Coordonnee arrivee) {
		this.graph = graph;
		this.depart = depart;
		this.arrivee = arrivee;
		this.taille = graph.getSize();
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Integer getTaille() {
		return taille;
	}

	public void setTaille(Integer taille) {
		this.taille = taille;
	}

	public Coordonnee getDepart() {
		return depart;
	}

	public void setDepart(Coordonnee depart) {
		this.depart = depart;
	}

	public Coordonnee getArrivee() {
		return arrivee;
	}

	public void setArrivee(Coordonnee arrivee) {
		this.arrivee = arrivee;
	}
}