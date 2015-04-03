package javabyrinthe;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javabyrinthe.serveur.*;
import java.util.ArrayList;

public class PartieManagerImpl implements PartieManager, Remote {
	ArrayList<Partie> partieList;
	String machine = "localhost";
	int port = 1099;
	Registry registry;
	public PartieManagerImpl() throws RemoteException{
		registry = LocateRegistry.getRegistry(machine, port);
		partieList = new ArrayList<Partie>();
	}
	
	public Partie creerPartie(String client, int nbJoueurMax) throws RemoteException {
		try {
	        Joueur joueur = (Joueur)registry.lookup(client);
			Partie nouvellePartie = new PartieImpl(nbJoueurMax);
			nouvellePartie.addJoueur(joueur);
			partieList.add(nouvellePartie);
			System.out.println("nouvelle partie "+ nouvellePartie);
			return nouvellePartie;
		} catch (Exception e) {
      		System.out.println(e);
   		}
   		return null;
	}

	public Partie rejoindrePartie(String client) throws RemoteException {
		try {
	        Joueur joueur = (Joueur)registry.lookup(client);
	        Partie choosenPartie=null;
	        for(Partie partie : partieList){
	        	if(partie.getnbJoueur()<partie.getnbJoueurMax()){
	        		partie.addJoueur(joueur);
	        		choosenPartie = partie;
					System.out.println("joueur ajouté à "+ partie + " état: "+partie.getnbJoueur() +"/"+ partie.getnbJoueurMax());
	        		if(partie.getnbJoueur() == partie.getnbJoueurMax()){
						System.out.println("Partie lancée");
	        			PartieRunner partieRunner = new PartieRunner(partie);
	        			partieRunner.run();
	        		}
	        		break;
	        	}
	        }
	        return choosenPartie;
		} catch (Exception e) {
      		System.out.println(e);
   		}
   		return null;
	}
}
