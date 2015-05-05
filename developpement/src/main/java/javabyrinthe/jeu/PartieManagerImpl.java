package javabyrinthe.jeu;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class PartieManagerImpl implements PartieManager, Remote {
	ArrayList<Partie> partieList;
	HashMap<String, JoueurInterface> joueurIAList;
	String machine = "localhost";
	int port = 1099;
	Registry registry;
	
	public PartieManagerImpl() throws RemoteException{
		registry = LocateRegistry.getRegistry(machine, port);
		partieList = new ArrayList<Partie>();
		joueurIAList = new HashMap<String, JoueurInterface>();
	}
	
	private JoueurInterface getJoueurFromIdClient(String stringIdClient) {
		try {
			if (joueurIAList.containsKey(stringIdClient)) {
				return joueurIAList.get(stringIdClient);
			} else {
	        	return (JoueurInterface)registry.lookup(stringIdClient);
	        }
	    } catch (Exception e) {
      		System.out.println(e);
   		}
		return null;
	}
		
	public Partie creerPartie(String clientId, String pseudo, String mode, int nbJoueurMax) throws RemoteException {
		try {
			Partie nouvellePartie = new PartieImpl(nbJoueurMax);
			partieList.add(nouvellePartie);
			System.out.println("Nouvelle partie ("+ nouvellePartie + ")");
			JoueurInterface joueur = (JoueurInterface)getJoueurFromIdClient(clientId);
			joueur.setPseudo(pseudo);
			nouvellePartie.addJoueur(joueur);
		   System.out.println("Ajout d'un joueur dans la partie "+ nouvellePartie + ". Etat nombre joueurs: "+ nouvellePartie.getnbJoueur()  + "/" + nouvellePartie.getnbJoueurMax());
			return nouvellePartie;
		} catch (Exception e) {
      		System.out.println("Erreur dans création de partie " + e);
   		}
   		return null;
	}

	public Partie rejoindrePartie(String clientId, String pseudo, String mode) throws RemoteException {
		try {
			JoueurInterface joueur = (JoueurInterface)registry.lookup(clientId);
			joueur.setPseudo(pseudo);
	        Partie choosenPartie = null;
	        for(Partie partie : partieList){
	        	if(partie.getnbJoueur() < partie.getnbJoueurMax()){
	        		partie.addJoueur(joueur);
	        		System.out.println("Ajout d'un joueur dans la partie: "+ partie + ". Etat: "+ partie.getnbJoueur()  + "/" + partie.getnbJoueurMax());
	        		choosenPartie = partie;
					
	        		if(partie.getnbJoueur() == partie.getnbJoueurMax()){
	        			System.out.println();
						System.out.println("########### Partie lancée ############");
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
