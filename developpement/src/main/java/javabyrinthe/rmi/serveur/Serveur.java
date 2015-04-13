package javabyrinthe.rmi.serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import javabyrinthe.jeu.PartieManager;
import javabyrinthe.jeu.PartieManagerImpl;

public class Serveur {
  public static void main(String args[]) {
    int port  = 1099;
     System.setProperty("sun.rmi.transport.tcp.responseTimeout","5000");//timeout pour appeler une méthode du client
    try {
        PartieManager stub = (PartieManager)UnicastRemoteObject.exportObject(new PartieManagerImpl(), 0);
        Registry registry = LocateRegistry.getRegistry(port);

        if(!Arrays.asList(registry.list()).contains("PartieManager"))
            registry.bind("PartieManager", stub);
        else
            registry.rebind("PartieManager", stub);
        System.out.println("Service PartieManager lié au registre");

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
