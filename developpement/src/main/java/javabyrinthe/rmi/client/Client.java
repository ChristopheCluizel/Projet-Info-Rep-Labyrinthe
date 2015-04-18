package javabyrinthe.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.UUID;

import javabyrinthe.jeu.Joueur;
import javabyrinthe.jeu.JoueurImpl;
import javabyrinthe.jeu.Partie;
import javabyrinthe.jeu.PartieManager;

public class Client {
  public static void main(String args[]) {
    String machine = "localhost";
    int port = 1099;
    int nbJoueurMaxParPartie = 2;

    try {
        Joueur stub = (Joueur)UnicastRemoteObject.exportObject(new JoueurImpl(),0);
        Registry registry = LocateRegistry.getRegistry(machine, port);
        String stringIdClient = UUID.randomUUID().toString();
        if(!Arrays.asList(registry.list()).contains(stringIdClient))
            registry.bind(stringIdClient, stub);
        else
            registry.rebind(stringIdClient, stub);
        System.out.println("Service " + stringIdClient + " lié au registre");

        PartieManager partieManager = (PartieManager)registry.lookup("PartieManager");
        Partie partie;
        if(args[0].contains("C"))
        	if(args[1].contains("H"))
        		partie = partieManager.creerPartie(stringIdClient, "christophe", "humain", nbJoueurMaxParPartie);
        	else
        		partie = partieManager.creerPartie(stringIdClient, "christopheIA", "IA", nbJoueurMaxParPartie);
        else if(args[0].contains("R"))
        	if(args[1].contains("H"))
        		partie = partieManager.rejoindrePartie(stringIdClient, "Paul", "humain");
        	else
        		partie = partieManager.rejoindrePartie(stringIdClient, "PaulIA", "IA");
    } catch (Exception e) {
       System.out.println("Client exception: " + e);
    }
  }
}
