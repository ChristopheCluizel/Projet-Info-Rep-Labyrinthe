package javabyrinthe.jeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import javabyrinthe.labyrinthe.Labyrinth;
import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.tools.Coordinate;

public class PartieImpl implements Partie, Remote {
	int nbJoueurMax = 2;
	int nbJoueur = 0;
	int nbTourMax = 50;
	int nbTour = 0;

	Labyrinth labyrinth;
	ArrayList<JoueurInterface> joueurList;

	public PartieImpl(int nbJoueurMax) throws RemoteException {
		this.nbJoueurMax = nbJoueurMax;
		this.joueurList = new ArrayList<JoueurInterface>();
		try {
			String randomMazeName = choisirLabyrinthe();
			this.labyrinth = new LabyrinthGenerator().loadLabyrinth("src/main/resources/mazes/" + randomMazeName);
			nbTourMax = this.labyrinth.getWidth() * this.labyrinth.getWidth(); // why not?
			
			System.out.println("######### Labyrinthe chargé ##########");
			System.out.println("Key departure: "
					+ new LabyrinthGenerator().coordinatesToKey(this.labyrinth.getDeparture(), this.labyrinth.getWidth())
					+ " -> " + this.labyrinth.getDeparture().toString());
			System.out.println("Key arrival: "
					+ new LabyrinthGenerator().coordinatesToKey(this.labyrinth.getArrival(), this.labyrinth.getWidth()) + " -> "
					+ this.labyrinth.getArrival().toString());
			System.out.println("######################################");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String choisirLabyrinthe() {
		Random rand = new Random();
		File file = new File("src/main/resources/mazes/");
		int max = file.list().length;

		int nombreAleatoire = rand.nextInt(max);
		return file.list()[nombreAleatoire];
	}

	public Labyrinth getLabyrinthe() {
		return labyrinth;
	}

	public String tourSuivant() throws RemoteException {
		this.nbTour++;
		for (JoueurInterface joueur : this.joueurList) {
			try {
				String choixDeplacement = joueur.jouer();
				Coordinate nextPosition = this.labyrinth.getNextSquareFromOrder(choixDeplacement, joueur.getActualPosition());
				if (this.labyrinth.isAuthorizedMove(joueur.getActualPosition(), nextPosition)) {
					joueur.setActualPosition(nextPosition);
					System.out.println("Tour " + this.nbTour + " -> déplacement vers " + choixDeplacement + " effectué.");
					if (joueur.getActualPosition().equals(this.labyrinth.getArrival())) {
						return joueur.getPseudo();
					}
				} else {
					System.out.println("Tour " + this.nbTour + " -> déplacement vers " + choixDeplacement + " impossible !");
				}
			} catch (Exception e) {
				System.out.println("Erreur tour: " + this.nbTour + " -> " + e);
			}
		}
		return "";
	}

	public void addJoueur(JoueurInterface joueur) throws RemoteException {
		joueur.setActualPosition(this.labyrinth.getDeparture());
		this.joueurList.add(joueur);
		this.nbJoueur++;
		for (JoueurInterface j : joueurList)
			j.setPartie(this);
	}

	public int getnbJoueurMax() {
		return this.nbJoueurMax;
	}

	public int getnbJoueur() {
		return this.nbJoueur;
	}

	public int getnbTourMax() {
		return this.nbTourMax;
	}

	public int getnbTour() {
		return this.nbTour;
	}

	public ArrayList<JoueurInterface> getJoueurs() {
		return this.joueurList;
	}
}
