package javabyrinthe;
import java.io.FileNotFoundException;

import javabyrinthe.labyrinthe.GenerateurLabyrinthe;
import javabyrinthe.labyrinthe.Labyrinthe;

public class MainClass {

	public static void main(String[] args) {
		try {
			Labyrinthe laby = new GenerateurLabyrinthe().chargerGraphe("src/main/resources/graph1.dot");
			laby.getGraph().display();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
