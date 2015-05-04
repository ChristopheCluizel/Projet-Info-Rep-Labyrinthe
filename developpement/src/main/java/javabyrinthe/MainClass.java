package javabyrinthe;

import java.rmi.RemoteException;

import javabyrinthe.jeu.CompilationFailedException;
import javabyrinthe.jeu.JoueurIA;

public class MainClass {

	public static void main(String[] args) {
		String code = "";
		try {
			JoueurIA ia = new JoueurIA("christophe", code);
			ia.init();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CompilationFailedException e) {
			e.printStackTrace();
		}
	}
}
