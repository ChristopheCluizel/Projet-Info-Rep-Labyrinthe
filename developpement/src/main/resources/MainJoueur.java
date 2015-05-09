import java.util.*;
import java.io.*;

public class MainJoueur {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entree, deplacement;
		while (true) {
			entree = sc.nextLine(); // lit l'entrée standard
			deplacement = choisirDirection(); // exécute la fonction écrite par le
																				// joueur
			System.out.println(deplacement); // affiche la direction souhaitée
		}
	}

	public static String choisirDirection() {
		int max = 4;
		int min = 1;
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(max - min + 1) + min;
		switch(nombreAleatoire)
		{
			case 1:
				return "HAUT";
			case 2:
				return "BAS";
			case 3:
				return "GAUCHE";
			case 4:
				return "DROITE";

		}
		return "HAUT"; // ou "BAS", "DROITE", "GAUCHE"
	}
}
