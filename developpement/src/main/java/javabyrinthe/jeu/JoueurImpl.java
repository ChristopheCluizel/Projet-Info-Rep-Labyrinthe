package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.tools.Coordinate;

public class JoueurImpl implements Joueur, Remote {
	
	String pseudo = "";
	Coordinate actualPosition;
	
	/** 
	 * Ask a player to play by entering his move order
	 * 
	 * @return the answer entered by the player
	 */
	public String jouer() throws RemoteException {
		System.out.println("A ton tour de jouer. Ta position actuelle : " + actualPosition.toString());
		Scanner sc = new Scanner(System.in);
		String answer = sc.next();
		return answer;
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
}
