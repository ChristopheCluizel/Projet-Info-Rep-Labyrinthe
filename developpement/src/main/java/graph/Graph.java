package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph {
	String name;
	Integer nbEdge;
	HashMap<Integer, ArrayList<Integer>> adjacence;
	
	public Graph(String name, Integer nbEdge) {
		this.name = name;
		this.nbEdge = nbEdge;
		adjacence = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	public HashMap<Integer, ArrayList<Integer>> getAdjacence() {
		return this.adjacence;
	}
	
	public Integer getSize() {
		return this.adjacence.size();
	}
	
	public void addNode(Integer key) {
		this.adjacence.put(key, new ArrayList<Integer>());
	}
	
	public void addEdge(Integer key1, Integer key2, Integer value) {
		this.adjacence.get(key1).add(key2);
	}
	
	public ArrayList<Integer> getSuccessors(Integer key) {
		return this.adjacence.get(key);
	}
	
	public Boolean nodePresent(Integer key) {
		return adjacence.containsKey(key);
	}
	
	public void display() {
		Iterator<Integer> i = this.adjacence.keySet().iterator();
		Integer clef = null;
		while (i.hasNext())
		{
		    clef = (Integer)i.next();
		    System.out.println("key : " + clef + ", Successors : " +
		    		getSuccessors(clef).toString());
		}
	}
}
