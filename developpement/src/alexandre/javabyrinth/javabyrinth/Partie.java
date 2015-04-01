package javabyrinth;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface Partie extends Remote, Serializable {
  //public Partie(int nbJoueurMax) throws RemoteException;
  public void tourSuivant() throws RemoteException;
  public void addJoueur(Joueur joueur) throws RemoteException;
  public int getnbJoueurMax();
  public int getnbJoueur();
  public int getnbTourMax();
  public int getnbTour();
}
