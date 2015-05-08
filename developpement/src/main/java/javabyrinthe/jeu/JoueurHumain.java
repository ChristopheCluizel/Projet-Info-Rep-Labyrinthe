package javabyrinthe.jeu;

import java.rmi.RemoteException;
import java.util.Scanner;

import javabyrinthe.ihm.GameWindow;

public class JoueurHumain extends Joueur {

	public JoueurHumain(String pseudo) throws RemoteException {
		super(pseudo);
		this.setTour(false);
	}

	@Override
	public String jouer() throws RemoteException {
		// game.setSelection("");
		// this.setTour(true);
		// // System.out.println("A ton tour de jouer. Ta position actuelle : " + actualPosition.toString());
		// // Scanner sc = new Scanner(System.in);
		// // String answer = sc.next();
		String answer = "";
		// do{
		// 	answer = game.getSelection();
		// }while(answer.equals(""));
		// this.setTour(false);
		return answer;
	}
}
