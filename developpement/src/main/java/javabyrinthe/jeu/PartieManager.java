package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface PartieManager extends Remote, Serializable {
 // public PartieManager() throws RemoteException;
  public Partie creerPartie(String clientId, String pseudo, String mode, int nbJoueurMax) throws RemoteException;
  public Partie rejoindrePartie(String clientId, String pseudo, String mode) throws RemoteException;
}
