package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import javabyrinthe.ihm.GameWindow;
import org.newdawn.slick.state.BasicGameState; 
import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.tools.Coordinate;

public abstract class Joueur implements JoueurInterface, Remote {
	
	String pseudo = "";
	Coordinate actualPosition;
	Partie partieEnCours;
	boolean aTonTour;
	
	
	public Joueur(String pseudo) throws RemoteException {
		this.setPseudo(pseudo);
		this.actualPosition = null;
		this.partieEnCours = null;
		this.aTonTour = false;
	}
	
	/** 
	 * Ask a player to play by entering his move order
	 * 
	 * @return the answer entered by the player
	 */
	public abstract String jouer() throws RemoteException;

	/**
	 * Tell the player that he won
	 */
	public void victoire() throws RemoteException {
		GameWindow.victoire();
	}

	/**
	 * Tell the player that he didn't won
	 */
	public void defaite() throws RemoteException {
		GameWindow.defaite();
	}

	/**
	 * Get the actual position of the player in the labyrinth
	 * 
	 * @return the coordinate of the square where the player is situated
	 */
	public Coordinate getActualPosition() throws RemoteException {
		return actualPosition;
	}

	/**
	 * Set the actual position of the player in the labyrinth
	 * 
	 * @param actualPosition the actual coordinate of the player
	 */
	public void setActualPosition(Coordinate actualPosition) throws RemoteException {
		this.actualPosition = actualPosition;
	}
	
	/**
	 *	Set the pseudo of the player
	 *
	 *	@param the pseudo of the player
	 */
	public void setPseudo(String pseudo) throws RemoteException {
		this.pseudo = pseudo;
	}
	
	/**
	 * 	Get the pseudo of the player
	 * 
	 * 	@return the pseudo of the player
	 */
	public String getPseudo() throws RemoteException {
		return this.pseudo;
	}
	
	public void setPartie(Partie partie) throws RemoteException {
		this.partieEnCours = partie;
	}
	
	public Partie getPartie() throws RemoteException {
		return this.partieEnCours;
	}

	public void setTour(boolean b) throws RemoteException{
		this.aTonTour = b;
	}

	public boolean getTour()throws RemoteException{
		return this.aTonTour;
	}
}
