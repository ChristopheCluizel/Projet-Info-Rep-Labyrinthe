package javabyrinthe.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.UUID;

import javabyrinthe.jeu.JoueurHumain;
import javabyrinthe.jeu.JoueurIA;
import javabyrinthe.jeu.JoueurInterface;
import javabyrinthe.jeu.Partie;
import javabyrinthe.jeu.PartieManager;

public class Client {
  public static void main(String args[]) {
    String machine = "localhost";
    int port = 1099;
    int nbJoueurMaxParPartie = 2;

    try {
        // création du joueur
        JoueurInterface stub ;
        if(args[1].contains("H"))
            stub = (JoueurInterface)UnicastRemoteObject.exportObject(new JoueurHumain("bob"),0);
        else
            stub = (JoueurInterface)UnicastRemoteObject.exportObject(new JoueurIA("bob"),0);
        
        // ajout du joueur au registre
        Registry registry = LocateRegistry.getRegistry(machine, port);
        String stringIdClient = UUID.randomUUID().toString();
        if(!Arrays.asList(registry.list()).contains(stringIdClient))
            registry.bind(stringIdClient, stub);
        else
            registry.rebind(stringIdClient, stub);
        System.out.println("Service " + stringIdClient + " lié au registre");

        // recupération du partie manager et entrée dans une parite
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
