package javabyrinthe;
import java.io.FileNotFoundException;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.labyrinthe.Labyrinth;

public class MainClass {

	public static void main(String[] args) {
		try {
			Labyrinth laby = new LabyrinthGenerator().loadLabyrinth("src/main/resources/graph1.dot");
			laby.getGraph().display();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
