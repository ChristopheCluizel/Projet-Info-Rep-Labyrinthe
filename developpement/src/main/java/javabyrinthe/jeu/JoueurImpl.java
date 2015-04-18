package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import javabyrinthe.labyrinthe.LabyrinthGenerator;
import javabyrinthe.tools.Coordinate;

public class JoueurImpl implements Joueur, Remote {
	
	String pseudo = "";
	Coordinate actualPosition;
	
	public JoueurImpl() throws RemoteException {
	}
	
	public String jouer() throws RemoteException {
		System.out.println("jouer");
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
}
