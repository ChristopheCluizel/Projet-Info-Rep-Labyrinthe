package javabyrinthe.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.UUID;

import javabyrinthe.*;

public class Client {
  public static void main(String args[]) {
    String machine = "localhost";
    int port = 1099;

    try {
        Joueur stub = (Joueur)UnicastRemoteObject.exportObject(new JoueurImpl(),0);
        Registry registry = LocateRegistry.getRegistry(machine, port);
        String id = UUID.randomUUID().toString();
        if(!Arrays.asList(registry.list()).contains(id))
            registry.bind(id, stub);
        else
            registry.rebind(id, stub);
        System.out.println("Service "+id+" lié au registre");


        PartieManager partieManager = (PartieManager)registry.lookup("PartieManager");
        Partie partie;
        if(args[0].contains("C"))
            partie=partieManager.creerPartie(id,2);
        else if(args[0].contains("R"))
            partie=partieManager.rejoindrePartie(id);

        

    } catch (Exception e) {
       System.out.println("Client exception: " +e);
    }
  }
}
