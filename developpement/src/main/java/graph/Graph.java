package graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graph is the class to represent a graph. It uses a list of adjacence nodes to
 * be lighter in memory
 * 
 * @author christophe
 */
public class Graph implements Serializable {

	String name;
	Integer nbEdge;
	HashMap<Integer, ArrayList<Integer>> adjacence;
	HashMap<Integer, Integer> nodes;

	/**
	 * Construct an empty Graph
	 * 
	 * @param name The name of the graph
	 * @param nbEdge The number of edges of the graph
	 */
	public Graph(String name) {
		this.name = name;
		this.nbEdge = 0;
		this.adjacence = new HashMap<Integer, ArrayList<Integer>>();
		this.nodes = new HashMap<Integer, Integer>();
	}

	/**
	 * Return all the nodes of the graph
	 * 
	 * @return a map of all the nodes of the graph
	 */
	public HashMap<Integer, Integer> getNodes() {
		return this.nodes;
	}

	/**
	 * Return the number of nodes in the graph
	 * 
	 * @return the number of nodes in the graph
	 */
	public Integer numberOfNodes() {
		return this.nodes.size();
	}

	/**
	 * Return the number of edges in the graph
	 * 
	 * @return the number of edges in the graph
	 */
	public Integer getNumberOfEdges() {
		return this.nbEdge;
	}

	/**
	 * Add a node in the graph
	 * 
	 * @param key the key of the node to add
	 */
	public void addNode(Integer key) {
		this.nodes.put(key, key);
		this.adjacence.put(key, new ArrayList<Integer>());
	}

	/**
	 * Add an edge in the graph
	 * 
	 * @param key1 the source key
	 * @param key2 the destination key
	 * @param value the value of the edge
	 */
	public void addEdge(Integer key1, Integer key2, Integer value) {
		this.adjacence.get(key1).add(key2);
		this.nbEdge += 1;
	}

	/**
	 * Get the successors of a node
	 * 
	 * @param key the key of the node that we want the predecessors
	 * @return a list of keys of nodes
	 */
	public ArrayList<Integer> getSuccessors(Integer key) {
		return this.adjacence.get(key);
	}

	/**
	 * Test if a node is present or not in the graph
	 * 
	 * @param key the key of the node we want to know if present in the graph
	 * @return if the node is present or not
	 */
	public Boolean nodePresent(Integer key) {
		return adjacence.containsKey(key);
	}

	/**
	 * Display all the nodes of the graph with its successors
	 */
	public void display() {
		Iterator<Integer> i = this.adjacence.keySet().iterator();
		Integer key = null;
		while (i.hasNext()) {
			key = (Integer) i.next();
			System.out.println("key : " + key + ", Successors : " + getSuccessors(key).toString());
		}
	}

	/**
	 * Redefine the toString method to describe a graph.
	 */
	@Override
	public String toString() {
		String string = getNumberOfEdges().toString() + "\n" + "graph " + name + " {\n";
		Iterator<Integer> i = this.adjacence.keySet().iterator();
		while (i.hasNext()) {
			Integer key = (Integer) i.next();
			for (int j = 0; j < this.adjacence.get(key).size(); j++) {
				string += key.toString() + " -> " + this.adjacence.get(key).get(j).toString() + "\n";
			}
		}
		string += "}";
		return string;
	}

	public String toStringForUser() {
		String string = getNumberOfEdges().toString() + "\n";
		Iterator<Integer> i = this.adjacence.keySet().iterator();
		while (i.hasNext()) {
			Integer key = (Integer) i.next();
			for (int j = 0; j < this.adjacence.get(key).size(); j++) {
				string += key.toString() + " " + this.adjacence.get(key).get(j).toString() + "\n";
			}
		}
		return string.substring(0, string.length() - 1);
	}
}
