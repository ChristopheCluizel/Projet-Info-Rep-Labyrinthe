package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.Scanner;

public class JoueurImpl implements Joueur, Remote {
	public String pseudo="";
	public JoueurImpl() throws RemoteException{
	}
	public String jouer() throws RemoteException {
		System.out.println("jouer");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		return ""+x;
	}
}
