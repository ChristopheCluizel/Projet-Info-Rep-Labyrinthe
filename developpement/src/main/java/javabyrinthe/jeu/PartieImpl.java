package javabyrinthe.jeu;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.labyrinthe.Labyrinth;

public class PartieImpl implements Partie, Remote {
  public int nbJoueurMax = 2;
  public int nbJoueur = 0;
  public int nbTourMax = 3;
  public int nbTour = 0;
  
  Labyrinth labyrinth;
  ArrayList<Joueur> joueurList;

	public PartieImpl(int nbJoueurMax) throws RemoteException{
		this.nbJoueurMax = nbJoueurMax;
		this.joueurList = new ArrayList<Joueur>();
		try {
			this.labyrinth = new LabyrinthGenerator().loadLabyrinth("graph1.dot");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Labyrinth getLabyrinthe() {
		return labyrinth;
	}

	public void tourSuivant() throws RemoteException {
		nbTour++;
		for(Joueur joueur : joueurList){
			try{
				String choix = joueur.jouer();
				System.out.println("tour:"+nbTour+" jeu "+choix);
			}catch(Exception e){
				System.out.println("tour "+nbTour + " -> "+e);
			}
		}
	}

  	public void addJoueur(Joueur joueur) throws RemoteException {
  		joueurList.add(joueur);
  		nbJoueur++;
		System.out.println(nbJoueur +"/"+nbJoueurMax);
  	}

  	public int getnbJoueurMax(){
  		return nbJoueurMax;
  	}
	public int getnbJoueur(){
  		return nbJoueur;
  	}
	public int getnbTourMax(){
  		return nbTourMax;
  	}
	public int getnbTour(){
  		return nbTour;
  	}
}
