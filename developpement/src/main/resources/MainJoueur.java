import java.util.*;
import java.io.*;

public class MainJoueur {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entree, deplacement;
		while(true) {
			deplacement = choisirDirection(); // exécute la fonction écrite par le joueur
			System.out.println(deplacement);
			entree = sc.nextLine(); // lit l'entrée standard
			deplacement = choisirDirection(); // exécute la fonction écrite par le joueur
			System.out.println(deplacement); // affiche la direction souhaitée
		}
	}
	 public static String choisirDirection() {
	 		return "HAUT"; // ou "BAS", "DROITE", "GAUCHE"
	 }
}

