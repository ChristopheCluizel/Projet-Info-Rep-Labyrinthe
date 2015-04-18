package javabyrinthe.jeu;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.labyrinthe.Labyrinth;
import javabyrinthe.tools.Coordinate;

public class PartieImpl implements Partie, Remote {
   int nbJoueurMax = 2;
   int nbJoueur = 0;
   int nbTourMax = 50;
   int nbTour = 0;
  
   Labyrinth labyrinth;
   ArrayList<Joueur> joueurList;

   public PartieImpl(int nbJoueurMax) throws RemoteException{
	   this.nbJoueurMax = nbJoueurMax;
	   this.joueurList = new ArrayList<Joueur>();
	   try {
		   this.labyrinth = new LabyrinthGenerator().loadLabyrinth("graph1.dot");
		   System.out.println("######### Labyrinthe chargé ##########");
		   System.out.println("Key departure: " + new LabyrinthGenerator().coordinatesToKey(this.labyrinth.getDeparture(), this.labyrinth.getSize()));
		   System.out.println("Key arrival: " + new LabyrinthGenerator().coordinatesToKey(this.labyrinth.getArrival(), this.labyrinth.getSize()));
		   System.out.println("######################################");
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
			   Coordinate nextPosition = this.labyrinth.getNextSquareFromOrder(choixDeplacement, joueur.getActualPosition());
			   if(this.labyrinth.isAuthorizedMove(joueur.getActualPosition(), nextPosition)) {
				   joueur.setActualPosition(nextPosition);
				   System.out.println("Tour " + this.nbTour + " -> déplacement vers " + choixDeplacement + " effectué.");
			   }
			   else {
				   System.out.println("Tour " + this.nbTour + " -> déplacement vers " + choixDeplacement + " impossible !");
			   }
			   
		   }catch(Exception e){
			   System.out.println("Erreur tour: " + this.nbTour + " -> " + e);
		   }
	   }
   }

   public void addJoueur(Joueur joueur) throws RemoteException {
	   joueur.setActualPosition(this.labyrinth.getDeparture());
	   this.joueurList.add(joueur);
	   this.nbJoueur++;
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
