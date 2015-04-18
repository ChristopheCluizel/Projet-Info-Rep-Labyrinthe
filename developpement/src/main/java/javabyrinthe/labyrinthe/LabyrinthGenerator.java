package javabyrinthe.labyrinthe;

import graph.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javabyrinthe.tools.Coordinate;

/**
 * LabyrinthGenerator allows to load a labyrinth from a file, to permute between key node of the graph and coordinate of the labyrinth and vice versa
 * 
 * @author christophe
 */
public class LabyrinthGenerator implements Serializable{
	
	/**
	 * Load a labyrinth from a text file
	 * 
	 * @param filePath the filePath of the labyrinth to load
	 * @return the labyrinth loaded
	 * @throws FileNotFoundException
	 * @see Graph
	 */
	public Labyrinth loadLabyrinth(String filePath) throws FileNotFoundException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			Integer nbEdges = Integer.parseInt(reader.readLine());
//			System.out.println("nb edges : " + nbEdges);
			String firstLine = reader.readLine().toString();
			String[] result = firstLine.split(" ");
			String graphName = result[1];
			
			Graph graph = new Graph(graphName, nbEdges);
			for(int i = 0; i < nbEdges; i++) {
				String[] keys = reader.readLine().split(" -> ");
				if(!graph.nodePresent(Integer.parseInt(keys[0])))
					graph.addNode(Integer.parseInt((keys[0])));
				graph.addEdge(Integer.parseInt(keys[0]), Integer.parseInt(keys[1]), 1);
			}
			reader.close();
//			System.out.println("size laby: " + graph.getSize());
			return new Labyrinth(graph, new Coordinate((int)Math.sqrt(graph.getSize()) / 2, (int)Math.sqrt(graph.getSize()) / 2), new Coordinate(0, 0)); // the departure is the center of the labyrinth
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Turn a key node of the graph into a coordinate of a square of the labyrinth
	 * 
	 * @param key the key of a node of the graph to convert in coordinates
	 * @param graphWidth the size of the graph (height and width are equal)
	 * @return a couple (x,y) which are the coordinates of a square of the labyrinth
	 * @see Coordinate
	 */
	public Coordinate keyToCoordinates(Integer key, Integer graphWidth) {
		return new Coordinate(key % graphWidth, key / graphWidth);
	}
	
	/**
	 * Turn a coordinate of a squate of the labyrinth into a key node of the graph
	 * 
	 * @param coordinate the coordinate of a square of the labyrinth
	 * @param graphWidth the size of the graph (height and width are equal)
	 * @return the key of a node of the graph
	 * @see Coordinate
	 */
	public Integer coordinatesToKey(Coordinate coordinate, Integer graphWidth) {
		return graphWidth * coordinate.getY() + coordinate.getX();
	}
}
