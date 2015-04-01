package javabyrinth;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface PartieManager extends Remote, Serializable {
 // public PartieManager() throws RemoteException;
  public Partie creerPartie(String client, int nbJoueurMax) throws RemoteException;
  public Partie rejoindrePartie(String client) throws RemoteException;
}
