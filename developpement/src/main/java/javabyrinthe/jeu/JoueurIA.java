package javabyrinthe.jeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;

public class JoueurIA extends Joueur {
	public String code;
	public String nomFichier = "MainJoueur";
	public Process processusAssocie;
	public BufferedWriter streamEnvoi;
	public BufferedReader streamReponse;
	public Boolean running;
	
	public JoueurIA(String pseudo) throws RemoteException, CompilationFailedException {
		super(pseudo);
		this.code = code;		
		init();
	}
	
	public void init() throws CompilationFailedException {
		String resCompilation = "";
		String errorCompilation = "";
		
		try {
			// Compilation du programme de l'IA
			final ProcessBuilder pb = new ProcessBuilder("javac",nomFichier+".java");
			Process processusCompilation = pb.start();
	
			// Vérification que la compilation c'est bien passée
			BufferedReader error = new BufferedReader(new InputStreamReader(processusCompilation.getErrorStream()));
			errorCompilation = error.readLine();
			if(errorCompilation != null)
			{
				running = false;
				throw new CompilationFailedException(errorCompilation);
			}
		
			// Démarrage du programme de l'IA
			final ProcessBuilder pb2 = new ProcessBuilder("java", nomFichier);
			processusAssocie = pb2.start();
			running = true;
			// Récupération des flux pour le dialogue
			this.streamReponse = new BufferedReader(new InputStreamReader(processusAssocie.getInputStream()));
			this.streamEnvoi = new BufferedWriter(new OutputStreamWriter(processusAssocie.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	

	public String jouer() throws RemoteException {
	// help : http://ydisanto.developpez.com/tutoriels/java/runtime-exec/
	// http://labs.excilys.com/2012./06/26/runtime-exec-pour-les-nuls-et-processbuilder/
		if(!running)
		{ 
			System.out.println("L'IA ne tourne pas.");
			return "";
		}
		System.out.println("A l'IA de jouer. Sa position actuelle : " + actualPosition.toString());
		String reponse = "";
		try {
			streamEnvoi.write("hello\n");//partieEnCours.getLabyrinthe().toString()); // on envoie les paramètres nécessaires pour exécuter la méthode choisirDirection écrite par le joueur
			streamEnvoi.newLine();
			System.out.println("Attente de réponse...");
			reponse = streamReponse.readLine();//!\\ le programme n' l'air de pas recevoir l'écriture
			if(reponse == null)
				reponse = "";
			System.out.println(reponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse; // retourne ce que le programme de l'IA affiche
	}
}
