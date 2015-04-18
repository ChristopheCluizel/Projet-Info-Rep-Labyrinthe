package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

import javabyrinthe.labyrinthe.Labyrinth;

public interface Partie extends Remote, Serializable {
  public String tourSuivant() throws RemoteException;
  public void addJoueur(Joueur joueur) throws RemoteException;
  public int getnbJoueurMax();
  public int getnbJoueur();
  public int getnbTourMax();
  public int getnbTour();
  public Labyrinth getLabyrinthe();
}
