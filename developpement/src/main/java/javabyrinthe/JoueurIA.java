package javabyrinthe;

import java.util.*;
import java.io.*;
import java.lang.Runtime;

public class JoueurIA implements Joueur {
	public String pseudo;
	public int x;
	public int y;
	public Process processusAssocie;
	
	public JoueurIA(String pseudo, String code) {
		this.pseudo = pseudo;
		this.x=0;
		this.y=0;
		
		try {
		// Création du fichier contenant le programme de l'IA
			String nomFichier = pseudo;//+".txt";
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(new String[] {"cp", "javabyrinthe/modele.txt", nomFichier}); // crée le fichier qui contiendra la programme de l'IA
			runtime.exec(new String[] {"sed", "-i","-e", "s/modele/"+pseudo+"/g", nomFichier}); // remplace le nom de la classe par le nouveau nom du fichier
						
			FileWriter fw = new FileWriter(nomFichier, true); // pour écrire à la fin du fichier
			BufferedWriter output = new BufferedWriter(fw);
			output.write(code); // écriture du code de l'IA écrit par le joueur
			output.write("\n}"); // Fermeture de la classe
			output.flush(); // envoi dans le fichier
			output.close(); // fermeture du fichier
		
		// Compilation du fichier
			runtime.exec(new String[] {"mv", nomFichier, nomFichier+".java"});
			runtime.exec(new String[] {"javac", nomFichier+".java"});
			
		// Lancement du programme de l'IA
			this.processusAssocie = runtime.exec(new String[] {"java", nomFichier});
			
		} catch (IOException e) {e.printStackTrace();} // A GERER !!!!!!
	}
	
	public String jouer() {
	// help : http://ydisanto.developpez.com/tutoriels/java/runtime-exec/
	// http://labs.excilys.com/2012./06/26/runtime-exec-pour-les-nuls-et-processbuilder/
	  String sortie = "";
	  try {
		new Thread() {
			public void run() {
				try {
		// On récupère le flux d'ENTREE de l'application externe
					BufferedWriter output = new BufferedWriter(new OutputStreamWriter(processusAssocie.getOutputStream()));
					output.write("paramètre du programme IA"); // on envoie les paramètres nécessaires pour exécuter la méthode choisirDirection écrite par le joueur
					output.flush();
					output.close();
				} catch (IOException e) {}
			}
		}.start();
		// On récupère le flux de SORTIE de l'application externe
		new Thread() {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(processusAssocie.getInputStream()));
					//sortie = reader.readLine(); TO SEE !!!
					reader.close();
				} catch (IOException e) {} // A GERER !!!!!!
			}
		}.start();
		this.processusAssocie.waitFor(); // ?
	  } catch (InterruptedException e) {} // A GERER !!!!!!
	  System.out.println(sortie); // debug
	  return sortie; // retourne ce que le programme de l'IA affiche
	}
}
