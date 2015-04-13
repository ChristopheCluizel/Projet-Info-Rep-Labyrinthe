package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface Joueur extends Remote, Serializable {
  public String pseudo="";
  //public Joueur(String pseudo) throws RemoteException;
  public String jouer() throws RemoteException;
}
