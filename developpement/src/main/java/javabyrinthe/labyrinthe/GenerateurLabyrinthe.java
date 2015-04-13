package javabyrinthe.labyrinthe;

import graph.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javabyrinthe.tools.Coordonnee;

public class GenerateurLabyrinthe {
	
	public Labyrinthe chargerGraphe(String cheminFichier) throws FileNotFoundException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
			Integer nbEdges = Integer.parseInt(reader.readLine());
			System.out.println("nb edges : " + nbEdges);
			String premiereLigne = reader.readLine().toString();
			String[] result = premiereLigne.split(" ");
			String nomGraph = result[1];
			
			Graph graph = new Graph(nomGraph, nbEdges);
			for(int i = 0; i < nbEdges; i++) {
				String[] keys = reader.readLine().split(" -> ");
				if(!graph.nodePresent(Integer.parseInt(keys[0])))
					graph.addNode(Integer.parseInt((keys[0])));
				graph.addEdge(Integer.parseInt(keys[0]), Integer.parseInt(keys[1]), 1);
			}
			reader.close();
			return new Labyrinthe(graph, new Coordonnee(0, 0), new Coordonnee(graph.getSize() / 2, graph.getSize() / 2));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
