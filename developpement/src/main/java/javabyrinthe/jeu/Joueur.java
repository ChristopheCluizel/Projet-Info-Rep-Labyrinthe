package javabyrinthe.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

import javabyrinthe.tools.Coordinate;

public interface Joueur extends Remote, Serializable {
  public String jouer() throws RemoteException;
  public void setPseudo(String pseudo) throws RemoteException;
  public String getPseudo() throws RemoteException;
  public Coordinate getActualPosition() throws RemoteException;
  public void setActualPosition(Coordinate actualPosition) throws RemoteException;
}
