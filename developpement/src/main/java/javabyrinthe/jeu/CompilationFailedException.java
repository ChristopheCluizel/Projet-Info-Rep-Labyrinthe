package javabyrinthe.jeu;

public class CompilationFailedException extends Exception {
	public CompilationFailedException() {
		super("La compilation du code envoyé a échoué.");
	}
}
