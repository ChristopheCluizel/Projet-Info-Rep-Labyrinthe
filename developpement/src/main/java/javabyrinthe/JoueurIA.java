package javabyrinthe;

import java.util.*;
import java.io.*;
import java.lang.Runtime;

public class JoueurIA implements Joueur {
	public String pseudo;
	public int x;
	public int y;
	
	public JoueurIA(String pseudo, String code) {
		this.pseudo = pseudo;
		this.x=0;
		this.y=0;
		
		try {
		// Création du fichier contenant le programme de l'IA
			String nomFichier = pseudo+".txt";
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(new String[] {"cp", "javabyrinthe/modele.txt", nomFichier}); // crée le fichier qui contiendra la programme de l'IA
			runtime.exec(new String[] {"sed", "-i -e", "s/modele/"+pseudo+"/g", nomFichier}); // remplace le nom de la classe par le nouveau nom du fichier
			//runtime.exec(new String[] {"echo", code, ">>", nomFichier}); // Ecrit le code à la fin du fichier
			
			//FileWriter fw = new FileWriter(nomFichier, true); // pour écrire à la fin du fichier
			//BufferedWriter output = new BufferedWriter(fw);
			//output.write(code); // écriture du code de l'IA écrit par le joueur
			//output.write("}"); // Fermeture de la classe
			//output.flush(); // envoi dans le fichier
			//output.close(); // fermeture du fichier
		
		// Compilation du fichier
			//runtime.exec(new String[] {"javac", pseudo+".java"});
			
		// Lancement du programme de l'IA
			//runtime.exec(new String[] {"java", pseudo});
			
		} catch (IOException e) {} // A GERER !!!!!!
	}
	
	public String jouer() {
		System.out.println(x+" "+y); // entre () doivent se trouver les paramètres nécessaires pour exécuter la méthode choisirDirection écrite par le joueur
		Scanner sc = new Scanner(System.in);
		return sc.next(); // retourne ce que le programme de l'IA affiche
	}
}
