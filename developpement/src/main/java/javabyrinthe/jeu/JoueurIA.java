package javabyrinthe.jeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;

public class JoueurIA extends Joueur {
	String nomFichier = "MainJoueur";
	Process processusAssocie;
	BufferedWriter streamEnvoi;
	BufferedReader streamReponse;
	Boolean running;

	public JoueurIA(String pseudo) throws RemoteException, CompilationFailedException {
		super(pseudo);
		init();
	}

	public void init() throws CompilationFailedException {
		String errorCompilation = "";

		try {
			// Compilation du programme de l'IA
			final ProcessBuilder pb = new ProcessBuilder("javac", nomFichier + ".java");
			pb.directory(new File("src/main/resources/"));
			Process processusCompilation = pb.start();

			// Vérification que la compilation c'est bien passée
			BufferedReader error = new BufferedReader(new InputStreamReader(processusCompilation.getErrorStream()));
			errorCompilation = error.readLine();
			if (errorCompilation != null) {
				running = false;
				throw new CompilationFailedException(errorCompilation);
			}

			// Démarrage du programme de l'IA
			final ProcessBuilder pb2 = new ProcessBuilder("java", nomFichier);
			pb2.directory(new File("src/main/resources/"));
			processusAssocie = pb2.start();
			running = true;
			// Récupération des flux pour le dialogue
			this.streamReponse = new BufferedReader(new InputStreamReader(processusAssocie.getInputStream()));
			this.streamEnvoi = new BufferedWriter(new OutputStreamWriter(processusAssocie.getOutputStream()));
		} catch (CompilationFailedException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String jouer() throws RemoteException {
		// help : http://ydisanto.developpez.com/tutoriels/java/runtime-exec/
		// http://labs.excilys.com/2012./06/26/runtime-exec-pour-les-nuls-et-processbuilder/
		if (!running) {
			System.out.println("L'IA ne tourne pas.");
			return "";
		}
		System.out.println("A l'IA de jouer. Sa position actuelle : " + actualPosition.toString());
		String reponse = "";
		try {
			Thread.sleep(100);
			streamEnvoi.flush();
			streamEnvoi.write(partieEnCours.getLabyrinthe().toString());
			// // on envoie les paramètres
			// nécessaires pour exécuter la
			// méthode choisirDirection écrite
			// par le joueur
			streamEnvoi.newLine();
			streamEnvoi.flush();
			System.out.println("Attente de réponse...");
			reponse = streamReponse.readLine();
			if (reponse == null)
				reponse = "";
			System.out.println(reponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse; // retourne ce que le programme de l'IA affiche
	}
}
