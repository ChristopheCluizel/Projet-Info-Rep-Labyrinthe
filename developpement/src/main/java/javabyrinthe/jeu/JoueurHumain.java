package javabyrinthe.jeu;

import java.rmi.RemoteException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

	@Override
	public String jouer() throws RemoteException {
		System.out.println("A ton tour de jouer. Ta position actuelle : " + actualPosition.toString());
		Scanner sc = new Scanner(System.in);
		String answer = sc.next();
		return answer;
	}

}
