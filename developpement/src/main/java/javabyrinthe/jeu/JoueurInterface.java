package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

import javabyrinthe.tools.Coordinate;

public interface JoueurInterface extends Remote, Serializable {
  public abstract String jouer() throws RemoteException;
  public void setPseudo(String pseudo) throws RemoteException;
  public String getPseudo() throws RemoteException;
  public Coordinate getActualPosition() throws RemoteException;
  public void setActualPosition(Coordinate actualPosition) throws RemoteException;
  public void setPartie(Partie partie) throws RemoteException;
  public Partie getPartie() throws RemoteException;
}
