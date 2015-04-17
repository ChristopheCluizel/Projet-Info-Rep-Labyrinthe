package javabyrinthe.jeu;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.labyrinthe.Labyrinth;

public class PartieImpl implements Partie, Remote {
   int nbJoueurMax = 2;
   int nbJoueur = 0;
   int nbTourMax = 3;
   int nbTour = 0;
  
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
	   this.nbTour++;
	   for(Joueur joueur : this.joueurList){
		   try{
			   String choixDeplacement = joueur.jouer();
			   System.out.println("tour:" + this.nbTour + " jeu " + choixDeplacement);
		   }catch(Exception e){
			   System.out.println("tour " + this.nbTour + " -> " + e);
		   }
	   }
   }

   public void addJoueur(Joueur joueur) throws RemoteException {
	   this.joueurList.add(joueur);
	   this.nbJoueur++;
	   System.out.println(this.nbJoueur + "/" + this.nbJoueurMax);
   }

   public int getnbJoueurMax(){
	   return this.nbJoueurMax;
   }
   public int getnbJoueur(){
	   return this.nbJoueur;
   }
   public int getnbTourMax(){
	   return this.nbTourMax;
   }
   public int getnbTour(){
	   return this.nbTour;
   }
}
