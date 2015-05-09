package javabyrinthe.jeu;

import java.rmi.RemoteException;
import java.util.Scanner;

import javabyrinthe.ihm.GameWindow;
import org.newdawn.slick.state.BasicGameState; 

public class JoueurHumain extends Joueur {

	public JoueurHumain(String pseudo) throws RemoteException {
		super(pseudo);
		this.setTour(false);
	}

	@Override
	public String jouer() throws RemoteException {
		this.setTour(true);
		String answer = GameWindow.recupererSelection();
		this.setTour(false);
		return answer;
	}
}
