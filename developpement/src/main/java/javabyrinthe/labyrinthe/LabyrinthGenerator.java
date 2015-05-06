package javabyrinthe.labyrinthe;

import graph.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javabyrinthe.tools.Coordinate;

/**
 * LabyrinthGenerator allows to load a labyrinth from a file, to permute between
 * key node of the graph and coordinate of the labyrinth and vice versa
 * 
 * @author christophe
 */
public class LabyrinthGenerator implements Serializable {

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
			String[] nameSizeArray = reader.readLine().split(" ");
			String name = nameSizeArray[0];
			int width = Integer.parseInt(nameSizeArray[1]);
			int height = Integer.parseInt(nameSizeArray[2]);
			String[] departureArray = reader.readLine().split(" ");
			Coordinate departure = new Coordinate(Integer.parseInt(departureArray[0]), Integer.parseInt(departureArray[1]));
			String[] arrivalArray = reader.readLine().split(" ");
			Coordinate arrival = new Coordinate(Integer.parseInt(arrivalArray[0]), Integer.parseInt(arrivalArray[1]));

			Integer nbEdges = Integer.parseInt(reader.readLine());
			String graphName = reader.readLine().split(" ")[1];

			Graph graph = new Graph(graphName);
			for (int i = 0; i < nbEdges; i++) {
				String[] keys = reader.readLine().split(" -> ");
				int key1 = Integer.parseInt(keys[0]);
				int key2 = Integer.parseInt(keys[1]);
				if (!graph.nodePresent(key1))
					graph.addNode(key1);
				if (!graph.nodePresent(key2))
					graph.addNode(key2);
				graph.addEdge(key1, key2, 1);
			}
			reader.close();
			// System.out.println("size laby: " + graph.getSize());
			return new Labyrinth(graph, departure, arrival, width, height, name);
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
	 * @return a couple (x,y) which are the coordinates of a square of the
	 *         labyrinth
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
