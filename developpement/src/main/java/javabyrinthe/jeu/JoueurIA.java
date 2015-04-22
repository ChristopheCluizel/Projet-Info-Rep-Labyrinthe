package javabyrinthe.jeu;

import java.rmi.RemoteException;
import java.util.*;
import java.io.*;
import java.lang.Runtime;

public class JoueurIA extends Joueur {
	public String code;
	public Process processusAssocie;
	public BufferedWriter envoiParam;
	public BufferedReader litReponse;
	
	public JoueurIA(String pseudo, String code) throws RemoteException, CompilationFailedException {
		super(pseudo);
		this.code = code;		
	}
	
	public void init() throws CompilationFailedException {
		String resCompilation = "";
		String nomFichier = pseudo; // A CHANGER ????
		
		try {
			// Compilation du programme de l'IA
			final ProcessBuilder pb = new ProcessBuilder("/bin/bash","buildIA.sh", nomFichier, code);
			pb.directory(new File("../../../resources"));
			Process processusCompilation = pb.start();
	
			// Vérification que la compilation c'est bien passée
			BufferedReader output = new BufferedReader(new InputStreamReader(processusCompilation.getInputStream()));
			resCompilation = output.readLine();
			output.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (resCompilation.equals("Compilation OK")) {
			try {
				// Démarrage du programme de l'IA
				final ProcessBuilder pb2 = new ProcessBuilder("/bin/bash","execIA.sh", nomFichier);
				pb2.directory(new File("../../../resources"));
				processusAssocie = pb2.start();
				// Récupération des flux pour le dialogue
				this.litReponse = new BufferedReader(new InputStreamReader(processusAssocie.getInputStream()));
				this.envoiParam = new BufferedWriter(new OutputStreamWriter(processusAssocie.getOutputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} else {
			throw new CompilationFailedException();
		}
	}
	

	public String jouer() {
	// help : http://ydisanto.developpez.com/tutoriels/java/runtime-exec/
	// http://labs.excilys.com/2012./06/26/runtime-exec-pour-les-nuls-et-processbuilder/
		String reponse = "";
		try {
			envoiParam.write(partieEnCours.getLabyrinthe().toString()); // on envoie les paramètres nécessaires pour exécuter la méthode choisirDirection écrite par le joueur
			envoiParam.flush();
			reponse = litReponse.readLine();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return reponse; // retourne ce que le programme de l'IA affiche
	}
}
